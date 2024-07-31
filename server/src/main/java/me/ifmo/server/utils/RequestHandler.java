package me.ifmo.server.utils;

import me.ifmo.common.interaction.Request;
import me.ifmo.common.interaction.Response;
import me.ifmo.common.interaction.ResponseCode;
import me.ifmo.server.utils.commands.Command;

import java.io.Serializable;
import java.util.Map;

public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    public Response handleRequest(Request request){
        String nameOfCommand = request.getCommandName();
        String argumentOfCommand = request.getArgumentOfCommand();
        Serializable transmittedObject = request.getTransmittedObject();
        ResponseCode responseCode = getResponseCode(nameOfCommand, argumentOfCommand, transmittedObject);
        return new Response(ResponseBodyFormatter.getResponse(), responseCode);
    }

    public ResponseCode getResponseCode(String nameOfCommand, String argumentOfCommand, Serializable transmittedObject){
        Map<String, Command> commands = this.commandManager.getCommands();
        if(commands.containsKey(nameOfCommand)){
            Command inputCommand = commands.get(nameOfCommand);
            if(inputCommand.hasValidArgument(argumentOfCommand, transmittedObject)){
                inputCommand.execute();
                if(nameOfCommand.equals("help")) this.commandManager.helpCommand();
                if(nameOfCommand.equals("history")) this.commandManager.historyOfCommands();
                if(nameOfCommand.equals("exit")) return ResponseCode.DISCONNECTED;
                this.commandManager.addToHistoryOfCommands(nameOfCommand);
                return ResponseCode.EXECUTED;
            } else {
                return ResponseCode.ERROR;
            }
        } else {
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("Wrong command input!");
            return ResponseCode.ERROR;
        }
    }
}
