package commands;

import Models.builders.VehicleBuilder;
import SystemTools.Console;
import exceptions.IncorrectInputEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;


    public Add(Console console, CollectionManager collectionManager) {
        super("add", "{element}", "Добавить элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            Integer nextId = collectionManager.GemerateIds();
            collectionManager.AddElementtoCollection(new VehicleBuilder(console, collectionManager).build());
            console.println("Транспорт успешно добавлен");
        } catch (IncorrectInputEX e) {
            console.printError(e.getMessage());
        }
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }

}