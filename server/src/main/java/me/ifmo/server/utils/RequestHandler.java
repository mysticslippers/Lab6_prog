package me.ifmo.server.utils;

import me.ifmo.common.interaction.Request;
import me.ifmo.common.interaction.Response;
import me.ifmo.common.interaction.ResponseCode;

import java.io.Serializable;

public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager){
        this.commandManager = commandManager;
    }

}
