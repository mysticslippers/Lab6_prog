package me.ifmo.common.interaction;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class responsible for interaction between client and server on the client side.
 */

public class Request implements Serializable{
    private String commandName;
    private String argumentOfCommand;
    private Serializable transmittedObject;

    /**
     * Private constructor for the Request class.
     */

    private Request(){
        this.commandName = "";
        this.argumentOfCommand = "";
        this.transmittedObject = null;
    }

    /**
     * Method for setting the new value of field commandName.
     * @param commandName - The new commandName value.
     */

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Method for setting the new value of field argumentOfCommand.
     * @param argumentOfCommand - The new argumentOfCommand value.
     */

    public void setArgumentOfCommand(String argumentOfCommand) {
        this.argumentOfCommand = argumentOfCommand;
    }

    /**
     * Method for setting the new value of field transmittedObject.
     * @param transmittedObject - The new transmittedObject value.
     */

    public void setTransmittedObject(Serializable transmittedObject) {
        this.transmittedObject = transmittedObject;
    }

    /**
     * Method for getting the value of the commandName field.
     * @return Returns value of the commandName field.
     */

    public String getCommandName() {
        return this.commandName;
    }

    /**
     * Method for getting the value of the argumentOfCommand field.
     * @return Returns value of the argumentOfCommand field.
     */

    public String getArgumentOfCommand() {
        return this.argumentOfCommand;
    }

    /**
     * Method for getting the value of the transmittedObject field.
     * @return Returns value of the transmittedObject field.
     */

    public Serializable getTransmittedObject() {
        return this.transmittedObject;
    }

    /**
     * Static factory method to create an empty Request instance.
     * @return Returns empty request without any information.
     */

    public static Request createEmptyRequest(){
        return new Request();
    }

    /**
     * Static factory method to create a Request instance with name and argument of command.
     * @return Returns default request.
     */

    public static Request createDefaultRequest(String commandName, String argumentOfCommand){
        Request request = new Request();
        request.setCommandName(commandName);
        request.setArgumentOfCommand(argumentOfCommand);
        return request;
    }

    /**
     * Static factory method to create a Request instance with name, argument and transmitted object of command.
     * @return Returns full complected request with all information.
     */

    public static Request createFullComplectedRequest(String commandName, String argumentOfCommand, Serializable transmittedObject){
        Request request = new Request();
        request.setCommandName(commandName);
        request.setArgumentOfCommand(argumentOfCommand);
        request.setTransmittedObject(transmittedObject);
        return request;
    }

    /**
     * A method that checks an object of type Request for emptiness.
     * @return Returns the result of checking for emptiness.
     */

    public boolean isRequestEmpty(){
        return (this.commandName.isEmpty()) && (this.argumentOfCommand.isEmpty()) && (this.transmittedObject == null);
    }

    /**
     * Overridden toString method for the Request class.
     * @return Returns information about an object of type Request.
     */

    @Override
    public String toString() {
        return "Request: COMMAND - " + getCommandName() + " || ARGUMENT - " + getArgumentOfCommand() + " || TRANSMITTED OBJECT - " + getTransmittedObject();
    }

    /**
     * Overridden hashCode method for the Request class.
     * @return Returns the hashcode of an object of type Request.
     */

    @Override
    public int hashCode() {
        return this.commandName.hashCode() + this.argumentOfCommand.hashCode() + this.transmittedObject.hashCode();
    }

    /**
     * Overridden equals method for the Request class.
     * @param obj - The object to be compared to.
     * @return Returns the result of comparing two objects of type Request.
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof Request requestObj){
            return (Objects.equals(this.commandName, requestObj.getCommandName())) && (Objects.equals(this.argumentOfCommand, requestObj.getArgumentOfCommand()))
                    && (Objects.equals(this.transmittedObject, requestObj.getTransmittedObject()));
        }
        return false;
    }
}
