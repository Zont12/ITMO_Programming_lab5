package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class Clear extends Command {
    private final CollectionManager collectionManager;

    private final Console console;

    public Clear(CollectionManager collectionManager, Console console) {
        super("clear", "Очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() {
        collectionManager.ClearCollection();
        console.println("Коллекция успешно очищена!");

    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
