package commands;

import SystemTools.Console;
import exceptions.RecursiveScriptEX;
import exceptions.WrongCommandArgsEX;
import managers.FileloaderManager;
import managers.InputManager;
import managers.ScriptModeManager;
import system.Main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;


public class ExecuteScript extends Command {

    public static String ScriptPath;

    private final Console console;

    public ExecuteScript(Console console) {
        super("execute_script", "file_name", "Считать скрипт и исполнить его");
        this.console = console;

    }

    @Override
    public void execute() throws Exception {
        InputManager.ChangeFileMode();
        File scriptFile = new FileloaderManager().loadFile(ScriptPath, "txt", "r", "script1");
        if (!ScriptModeManager.scriptStack.isEmpty() && ScriptModeManager.scriptStack.contains(ScriptPath))
            throw new RecursiveScriptEX("Скрипт рекурсивен! Измените скрипт!");
        ScriptModeManager.scriptStack.push(ScriptPath);
        Scanner prevscanner = InputManager.getUserScanner();
        Scanner scanner = new Scanner(new FileInputStream(scriptFile));
        InputManager.setUserScanner(scanner);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("add")) {
                    String[] values = new String[10]; // Обратите внимание: вам нужно 10 значений, а не 8
                    for (int i = 0; i < 7; i++) {
                        if (scanner.hasNextLine()) {
                            values[i] = scanner.nextLine().trim();
                        } else {
                            throw new Exception("Ошибка: для команды 'add' необходимо ввести не меньше 7 значений значений");
                        }
                    }
                    // Здесь можно обработать массив значений values, например, добавить в базу данных или список
                }
            }
            Main.scriptMode();
            console.println("Скрипт был исполнен");
        } catch (Exception e) {
            console.printError(e.getMessage());
        } finally {
            ScriptModeManager.scriptStack.pop();
            ScriptModeManager.SCRIPT_MODE = false;
            InputManager.setUserScanner(prevscanner); // Возвращаем предыдущий сканнер
            InputManager.ChangeFileMode();
        }
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 1) throw new WrongCommandArgsEX("У данной команды должен быть 1 аргумент");
        ScriptPath = args[0];
    }

}


