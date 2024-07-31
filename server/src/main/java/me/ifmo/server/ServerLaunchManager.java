package me.ifmo.server;

import me.ifmo.common.exceptions.FailedConnectionException;
import me.ifmo.common.exceptions.NullServerSocketException;
import me.ifmo.common.exceptions.ServerSocketException;
import me.ifmo.common.interaction.Request;
import me.ifmo.common.interaction.Response;
import me.ifmo.common.interaction.ResponseCode;
import me.ifmo.server.utils.CollectionManager;
import me.ifmo.server.utils.CommandManager;
import me.ifmo.server.utils.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerLaunchManager {
    private static final int PORT = 1488;
    private static final int TIME_OUT = 60000;
    private ServerSocket serverSocket;
    private final RequestHandler handler;

    public ServerLaunchManager(RequestHandler handler){
        this.handler = handler;
    }

    private void openServerSocket() throws ServerSocketException {
        try {
            System.out.println("Starting server...");
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(TIME_OUT);
            System.out.println("The server has been launched successfully!");
        } catch (IllegalArgumentException exception){
            System.out.println("The current port value is outside the range of possible values!");
            throw new ServerSocketException();
        } catch(IOException exception){
            System.out.println("Error opening socket using this port!");
            throw new ServerSocketException();
        }
    }

    private void closeServerSocket(){
        try{
            System.out.println("Shutting down server...");
            if(serverSocket == null) throw new NullServerSocketException();
            serverSocket.close();
            System.out.println("Server is shutted down successfully!");
        }catch(NullServerSocketException exception){
            System.out.println("Server socket not found! Try starting the server again!");
        }catch(IOException exception){
            System.out.println("Error closing socket using this port!");
        }
    }

    private Socket connect() throws FailedConnectionException, SocketTimeoutException {
        try{
            System.out.println("Listening to port " + PORT + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection with the client established!");
            return clientSocket;
        }catch(SocketTimeoutException exception){
            System.out.println("Connection timed out!");
            throw new SocketTimeoutException();
        }catch(IOException exception){
            System.out.println("Failed to establish connection with the client!");
            throw new FailedConnectionException();
        }
    }

    private boolean handleRequest(Socket clientSocket){
        Request request = null;
        Response response = null;
        try(ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream())){
            do{
                request = (Request) is.readObject();
                response = handler.handleRequest(request);
                System.out.println("The client's request was successfully processed.");
                os.writeObject(response);
                os.flush();
            }while(response.getResponseCode() != ResponseCode.DISCONNECTED);
            return false;
        }catch(ClassNotFoundException exception){
            System.out.println("An error occurred while reading the received data!");
        }catch(InvalidClassException | NotSerializableException exception){
            System.out.println("An error occurred while sending data to the client!");
        }catch(IOException exception){
            if(request == null)
                System.out.println("Unexpected connection loss with client!");
            else
                System.out.println("The client has successfully disconnected from the server!");
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
                    System.out.println("An error occurred while trying to disconnect from the client!");
                }
            }
            closeServerSocket();
        }catch(ServerSocketException exception){
            System.out.println("The server cannot be started!");
        }
    }
}
