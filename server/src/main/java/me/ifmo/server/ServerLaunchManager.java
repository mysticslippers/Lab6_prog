package me.ifmo.server;

import me.ifmo.common.data.Dragon;
import me.ifmo.common.exceptions.FailedConnectionException;
import me.ifmo.common.exceptions.NullServerSocketException;
import me.ifmo.common.exceptions.ServerSocketException;
import me.ifmo.common.interaction.Request;
import me.ifmo.common.interaction.Response;
import me.ifmo.common.interaction.ResponseCode;
import me.ifmo.server.utils.CollectionManager;
import me.ifmo.server.utils.CommandManager;
import me.ifmo.server.utils.RequestHandler;
import me.ifmo.server.utils.commands.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

public class ServerLaunchManager {
    private static final int PORT = 1488;
    private static final int TIME_OUT = 60000;
    private ServerSocket serverSocket;
    private final CommandManager commandManager;
    private final RequestHandler handler;

    public ServerLaunchManager(String filePath){
        Path path = Paths.get(filePath).toAbsolutePath();
        LinkedHashSet<Dragon> collection = new LinkedHashSet<>();
        CollectionManager collectionManager = new CollectionManager(collection);
        collectionManager.loadCollectionFromFile(path.toString());
        this.commandManager = new CommandManager(new AddCommand(collectionManager), new AddIfMaxCommand(collectionManager),
                new AverageOfAgeCommand(collectionManager), new ClearCommand(collectionManager), new ExecuteScriptCommand(),
                new ExitCommand(), new HelpCommand(), new HistoryCommand(), new InfoCommand(collectionManager),
                new PrintFieldDescendingCaveCommand(collectionManager), new RemoveByCharacterCommand(collectionManager),
                new RemoveByIdCommand(collectionManager), new ReorderCommand(collectionManager),
                new SaveCommand(collectionManager), new ShowCommand(collectionManager), new UpdateByIdCommand(collectionManager));

        this.handler = new RequestHandler(commandManager);
    }

    private void openServerSocket() throws ServerSocketException {
        try {
            ServerApp.logger.info("Starting server...");
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(TIME_OUT);
            ServerApp.logger.info("The server has been launched successfully!");
        } catch (IllegalArgumentException exception){
            ServerApp.logger.fatal("The current port value is outside the range of possible values!");
            throw new ServerSocketException();
        } catch(IOException exception){
            ServerApp.logger.fatal("Error opening socket using this port!");
            throw new ServerSocketException();
        }
    }

    private void closeServerSocket(){
        try{
            ServerApp.logger.info("Shutting down server...");
            if(serverSocket == null) throw new NullServerSocketException();
            serverSocket.close();
            this.commandManager.saveHistoryOfCommands();
            this.commandManager.getCommands().get("save").execute();
            ServerApp.logger.info("Server is shutted down successfully!");
        }catch(NullServerSocketException exception){
            ServerApp.logger.error("Server socket not found! Try starting the server again!");
        }catch(IOException exception){
            ServerApp.logger.error("Error closing socket using this port!");
        }
    }

    private Socket connect() throws FailedConnectionException, SocketTimeoutException {
        try{
            ServerApp.logger.info("Listening to port " + PORT + "...");
            Socket clientSocket = serverSocket.accept();
            ServerApp.logger.info("Connection with the client established!");
            return clientSocket;
        }catch(SocketTimeoutException exception){
            ServerApp.logger.warn("Connection timed out!");
            throw new SocketTimeoutException();
        }catch(IOException exception){
            ServerApp.logger.error("Failed to establish connection with the client!");
            throw new FailedConnectionException();
        }
    }

    private boolean handleRequest(Socket clientSocket){
        Request request = null;
        Response response;
        try(ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream())){
            do{
                request = (Request) is.readObject();
                response = handler.handleRequest(request);
                ServerApp.logger.info("The client's request was successfully processed.");
                os.writeObject(response);
                os.flush();
            }while(response.getResponseCode() != ResponseCode.DISCONNECTED);
            return false;
        }catch(ClassNotFoundException exception){
            ServerApp.logger.error("An error occurred while reading the received data!");
        }catch(InvalidClassException | NotSerializableException exception){
            ServerApp.logger.error("An error occurred while sending data to the client!");
        }catch(IOException exception){
            if(request == null)
                ServerApp.logger.warn("Unexpected connection loss with client!");
            else
                ServerApp.logger.info("The client has successfully disconnected from the server!");
        }
        return true;
    }

    public void launch(){
        boolean process = true;
        try{
            openServerSocket();
            while (process){
                try(Socket clientSocket = connect()){
                    process = handleRequest(clientSocket);
                }catch(FailedConnectionException | SocketTimeoutException exception){
                    break;
                }catch(IOException exception){
                    ServerApp.logger.error("An error occurred while trying to disconnect from the client!");
                }
            }
            closeServerSocket();
        }catch(ServerSocketException exception){
            ServerApp.logger.fatal("The server cannot be started!");
        }
    }
}
