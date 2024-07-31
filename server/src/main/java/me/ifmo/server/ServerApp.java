package me.ifmo.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class ServerApp {
    public static Logger logger = LogManager.getLogger("Server");
    public static void main(String[] args) {
        if(Objects.equals(args[0], "CollectionCSV.csv") || Objects.equals(args[0], "CollectionJSON.json") || Objects.equals(args[0], "CollectionXML.xml")){
            ServerLaunchManager serverLaunchManager = new ServerLaunchManager(args[0]);
            serverLaunchManager.launch();
        }
    }
}