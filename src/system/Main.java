package system;

import Models.Vehicle;
import Models.builders.VehicleBuilder;
import SystemTools.Console;
import SystemTools.Runner;
import commands.Command;
import managers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

import static commands.ExecuteScript.ScriptPath;


public class Main {

    private static CollectionManager collectionManager;

    private static CommandManager commandManager;

    private static VehicleBuilder vehicleBuilder;

    private static DumpManager dumpManager;


    public static void main(String[] args) {
        InputManager.setUserScanner(new Scanner(System.in));
        Console console = new Console();
        collectionManager = new CollectionManager(loadData());
        collectionManager.cleanAttributesInCollection();
        vehicleBuilder = new VehicleBuilder(console, collectionManager);
        commandManager = new CommandManager(console, collectionManager, vehicleBuilder, dumpManager);
        new Runner(console, commandManager).interactiveMode();
    }

    public static String readFileName() {
        Console console = new Console();
        String dataFilePath = System.getenv("FILE_NAME");
        if (dataFilePath == null) {
            console.printError("Переменная окружения не определена!");
            System.exit(0);
        }
        return dataFilePath;
    }

    public static PriorityQueue<Vehicle> loadData() {
        String dataFilePath = readFileName();

        PriorityQueue<Vehicle> data = null;
        File dataFile = null;

        Console console = new Console();

        try {
            dataFile = new FileloaderManager().loadFile(dataFilePath, "json", "rw", "file");
        } catch (FileNotFoundException e) {
            console.printError(e.getMessage());
            System.exit(0);
        }

        dumpManager = new DumpManager(dataFile);
        try {
            data = dumpManager.readJSON();
        } catch (Exception e) {
            console.printError("При выборе переменной окружения произошли ошибка! Пожалуйста проверьте свою переменную окружения!");
            System.exit(0);
        }

        if (data == null) data = new PriorityQueue<>();


        if (!CollectionManager.isValid(data)) {
            console.printError("Файл недействителен");
        }
        console.println("Переменная окружения успешно определена!");
        console.println("Теперь вы можете вводить команды!");
        return data;
    }

    public static void scriptMode() throws Exception {
        InputManager.setUserScanner(new Scanner(new File(ScriptPath)));
        while (InputManager.getUserScanner().hasNextLine()) {
            String Startread = InputManager.readLine();
            String[] input = (Startread.trim() + " ").split(" ");
            if (input.length == 0) continue;
            String commandName = input[0];
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);
            Command command = commandManager.launchCommand(commandName, commandArgs);
            command.execute();

        }
    }


}

