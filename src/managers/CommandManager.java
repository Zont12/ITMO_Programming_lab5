package managers;

import Models.builders.VehicleBuilder;
import SystemTools.Console;
import commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CommandManager {
    private final ArrayList<Command> CommandList;
    private final Console console;


    public CommandManager(Console console, CollectionManager collectionManager, VehicleBuilder vehicleBuilder, DumpManager dumpManager) {
        this.console = console;
        this.CommandList = new ArrayList<>(Arrays.asList(
                new Add(console, collectionManager),
                new Exit(console, collectionManager),
                new Save(dumpManager, collectionManager, console),
                new ExecuteScript(console),
                new RemoveById(collectionManager, console),
                new Clear(collectionManager, console),
                new RemoveFirst(collectionManager, console),
                new Show(console, collectionManager),
                new Min_By_Number_Of_Wheels(collectionManager, console),
                new Count_by_fuel_type(collectionManager, console),
                new Print_unique_engine_power(collectionManager, console),
                new Execute_add_if_max(collectionManager, console),
                new Add_if_min(collectionManager, console),
                new UpdateID(collectionManager, console),
                new Help(this, console),
                new Info(collectionManager, console)

        ));
    }


    public ArrayList<Command> getCommandList() {
        return CommandList;
    }


    public Command launchCommand(String initialCommandName, String[] commandArgs) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String commandName = initialCommandName;

        while (true) {
            final String currentCommandName = commandName;
            boolean commandExists = this.CommandList.stream()
                    .anyMatch(userCommand -> userCommand.getName().equals(currentCommandName));
            if (!InputManager.isFileMode()) {
                Command command = this.CommandList.stream()
                        .filter(userCommand -> userCommand.getName().equals(currentCommandName))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Комманды " + currentCommandName + " не существует! Введите команду заново:"));

                command.initCommandArgs(commandArgs);
                return command;

            } else {
                Command command = this.CommandList.stream()
                        .filter(userCommand -> userCommand.getName().equals(currentCommandName))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Комманды " + currentCommandName + " не существует! Ошибка в скрипте! Измените скрипт!"));

                command.initCommandArgs(commandArgs);
                return command;
            }

        }
    }
}
