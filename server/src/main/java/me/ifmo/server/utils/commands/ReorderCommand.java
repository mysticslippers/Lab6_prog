package me.ifmo.server.utils.commands;

import me.ifmo.common.exceptions.WrongArgumentException;
import me.ifmo.server.utils.CollectionManager;
import me.ifmo.server.utils.ResponseBodyFormatter;

/**
 * The class that implements the reorder command.
 */

public class ReorderCommand extends BaseCommand{
    CollectionManager collectionManager;

    /**
     * The reorder command constructor.
     * @param collectionManager - Collection manager.
     */

    public ReorderCommand(CollectionManager collectionManager){
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
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
        try {
            if (!argument.isEmpty() || receivedDragon != null) throw new WrongArgumentException();
        }catch(WrongArgumentException exception){
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("This command does not contain an argument!");
            ResponseBodyFormatter.addResponseText("This command does contain an object!");
            valid = false;
        }catch(IllegalArgumentException exception){
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("Please enter a non-empty value!");
            valid = false;
        }
        return valid;
    }

    /**
     * Method that executes the reorder command.
     * @return Returns true if the command completed successfully.
     */

    @Override
    public boolean execute(){
        ResponseBodyFormatter.addResponseText("----------------------");
        ResponseBodyFormatter.addResponseText("The collection is sorted in reverse order.");
        this.collectionManager.resortByCave();
        return true;
    }
}