package me.ifmo.server.utils.commands;

import me.ifmo.common.exceptions.WrongArgumentException;
import me.ifmo.server.ServerApp;
import me.ifmo.server.utils.CollectionManager;
import me.ifmo.server.utils.ResponseBodyFormatter;

/**
 * The class that implements the save command.
 */

public class SaveCommand extends BaseCommand{
    CollectionManager collectionManager;

    /**
     * The save command constructor.
     * @param collectionManager - Collection manager.
     */

    public SaveCommand(CollectionManager collectionManager){
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    /**
     * A method that checks the validity of an argument.
     * @param argument - The command argument.
     * @param receivedDragon - The object passed by the client.
     * @return Returns true if the argument is valid.
     */

    @Override
    public boolean hasValidArgument(String argument, Object receivedDragon){
        boolean valid = true;
        try{
            if(!argument.isEmpty() || receivedDragon != null) throw new WrongArgumentException();
        }catch(WrongArgumentException exception){
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("This command does not contain an argument!");
            ResponseBodyFormatter.addResponseText("This command does not contain an object!");
            valid = false;
        }
        return valid;
    }

    /**
     * The method that executes the save command.
     * @return Returns true if the command completed successfully.
     */

    @Override
    public boolean execute() {
        ServerApp.logger.info("Collection saved to file!");
        this.collectionManager.saveCollectionToFile();
        return true;
    }
}