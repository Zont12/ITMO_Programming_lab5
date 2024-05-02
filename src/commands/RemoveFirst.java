package commands;

import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class RemoveFirst extends Command {
    private CollectionManager collectionManager;

    private final Console console;

    public RemoveFirst(CollectionManager collectionManager, Console console) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() throws CollectionisEmptyEX {
        if (this.collectionManager.getCollection().isEmpty()) throw new CollectionisEmptyEX("Коллекция пуста!");
        collectionManager.RemoveFirstElemFromCollection();
        console.println("Первый элемент коллекции успешно удален!");

    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
