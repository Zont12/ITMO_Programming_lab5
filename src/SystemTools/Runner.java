package SystemTools;

import commands.Command;
import managers.CommandManager;
import managers.InputManager;

import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private final Console console;
    private final CommandManager commandManager;


    public Runner(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }


    public void interactiveMode() { // запуск программы
        InputManager.setUserScanner(new Scanner(System.in));
        while (InputManager.getUserScanner().hasNextLine()) {
            String StartRead = InputManager.readLine();
            String[] input = (StartRead.trim() + " ").split(" ");
            if (input.length == 0) continue;
            String commandName = input[0];
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);
            try {
                Command command = commandManager.launchCommand(commandName, commandArgs);
                command.execute();
            } catch (Exception e) {
                console.printError(e.getMessage());
            }
        }
    }

}