package me.ifmo.server.utils.commands;

import me.ifmo.common.exceptions.CollectionNotRecognizedException;
import me.ifmo.common.exceptions.WrongArgumentException;
import me.ifmo.server.utils.CollectionManager;
import me.ifmo.server.utils.ResponseBodyFormatter;

/**
 * A class that implements the average_of_age command.
 */

public class AverageOfAgeCommand extends BaseCommand{
    CollectionManager collectionManager;

    /**
     * The average_of_age command constructor.
     * @param collectionManager - Collection manager.
     */

    public AverageOfAgeCommand(CollectionManager collectionManager){
        super("average_of_age", "вывести среднее значение поля age для всех элементов коллекции");
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
            if(this.collectionManager.getCollection().isEmpty()) throw new CollectionNotRecognizedException();
        }catch(WrongArgumentException exception){
            System.out.println("----------------------");
            ResponseBodyFormatter.addResponseText("This command does not contain an argument!");
            ResponseBodyFormatter.addResponseText("This command does not contain an object!");
            valid = false;
        }catch(CollectionNotRecognizedException exception){
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("The collection is empty!");
            valid = false;
        }
        return valid;
    }

    /**
     * The method that executes the average_of_age command.
     * @return Returns true if the command completed successfully.
     */

    @Override
    public boolean execute(){
        ResponseBodyFormatter.addResponseText("----------------------");
        ResponseBodyFormatter.addResponseText("Here is the average price: ");
        this.collectionManager.averageOfAge();
        return true;
    }
}