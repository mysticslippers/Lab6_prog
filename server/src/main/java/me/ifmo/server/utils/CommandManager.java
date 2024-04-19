package me.ifmo.server.utils;

import me.ifmo.server.utils.commands.Command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that implements the work of a command manager.
 */

public class CommandManager{
    private final Map<String, Command> commands = new HashMap<>();
    private final ArrayList<String> historyOfCommands = new ArrayList<>();
    private final ArrayList<String> namesOfScripts = new ArrayList<>();
    private final Command addCommand;
    private final Command addIfMaxCommand;
    private final Command averageOfAgeCommand;
    private final Command clearCommand;
    private final Command executeScriptCommand;
    private final Command exitCommand;
    private final Command helpCommand;
    private final Command historyCommand;
    private final Command infoCommand;
    private final Command printFieldDescendingCaveCommand;
    private final Command removeByIdCommand;
    private final Command removeByCharacterCommand;
    private final Command reorderCommand;
    private final Command showCommand;
    private final Command updateByIdCommand;

    /**
     * Constructor linking commands and CommandManager.
     * @param addCommand - The add {element} command.
     * @param addIfMaxCommand - The add_if_max {element} command.
     * @param averageOfAgeCommand - The average_of_age command.
     * @param clearCommand - The clear command.
     * @param executeScriptCommand - The execute_script file_name command.
     * @param exitCommand - The exit command.
     * @param helpCommand - The help command.
     * @param historyCommand - The history command.
     * @param infoCommand - The info command.
     * @param printFieldDescendingCaveCommand - The print_field_descending_cave command.
     * @param removeByIdCommand - The remove_by_id id command.
     * @param removeByCharacterCommand - The remove_all_by_character character command.
     * @param reorderCommand - The reorder command.
     * @param showCommand - The show command.
     * @param updateByIdCommand - The update_by_id id {element}.
     */

    public CommandManager(Command addCommand, Command addIfMaxCommand, Command averageOfAgeCommand,
                          Command clearCommand, Command executeScriptCommand, Command exitCommand, Command helpCommand,
                          Command historyCommand, Command infoCommand, Command printFieldDescendingCaveCommand,
                          Command removeByIdCommand, Command removeByCharacterCommand, Command reorderCommand,
                          Command showCommand, Command updateByIdCommand){
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.averageOfAgeCommand = averageOfAgeCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.helpCommand = helpCommand;
        this.historyCommand = historyCommand;
        this.infoCommand = infoCommand;
        this.printFieldDescendingCaveCommand = printFieldDescendingCaveCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeByCharacterCommand = removeByCharacterCommand;
        this.reorderCommand = reorderCommand;
        this.showCommand = showCommand;
        this.updateByIdCommand = updateByIdCommand;

        putCommands();
    }

    /**
     * Method for filling Map with commands and their names.
     */

    public void putCommands(){
        commands.put("add", addCommand);
        commands.put("add_if_max", addIfMaxCommand);
        commands.put("average_of_age", averageOfAgeCommand);
        commands.put("clear", clearCommand);
        commands.put("execute_script", executeScriptCommand);
        commands.put("exit", exitCommand);
        commands.put("help", helpCommand);
        commands.put("history", historyCommand);
        commands.put("info", infoCommand);
        commands.put("print_field_descending_cave", printFieldDescendingCaveCommand);
        commands.put("remove_by_id", removeByIdCommand);
        commands.put("remove_all_by_character", removeByCharacterCommand);
        commands.put("reorder", reorderCommand);
        commands.put("show", showCommand);
        commands.put("update_by_id", updateByIdCommand);
    }

    /**
     * Method for getting a Map with commands.
     * @return Returns dictionary with command names and their meaning.
     */

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    /**
     * Method for getting command history.
     * @return Returns a list of previously used commands.
     */

    public ArrayList<String> getHistoryOfCommands(){
        return this.historyOfCommands;
    }

    /**
     * Method for adding commands to history.
     * @param command - The name of used command.
     */

    public void addToHistoryOfCommands(String command){
        this.historyOfCommands.add(command);
    }

    /**
     * A method for saving a collection of commands to a file before exiting the program.
     */

    public void saveHistoryOfCommands(){
        try(FileWriter fileWriter = new FileWriter(System.getenv("history"))){
            for(String command : getHistoryOfCommands()){
                fileWriter.write(command + "\n");
            }
            fileWriter.flush();
        }catch(IOException exception){
            ResponseBodyFormatter.addResponseText("----------------------");
            ResponseBodyFormatter.addResponseText("Please do not delete the file!");
        }
    }

    /**
     * The method that executes the help command.
     */

    public void helpCommand(){
        for(Command command : this.commands.values()){
            ResponseBodyFormatter.addResponseText(command.getName() + ": " + command.getDescription());
        }
    }

    /**
     * The method that executes the history command.
     */

    public void historyOfCommands(){
        for(String command : getHistoryOfCommands()){
            ResponseBodyFormatter.addResponseText(command);
        }
    }
}