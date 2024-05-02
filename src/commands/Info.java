package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class Info extends Command {
    private final CollectionManager collectionManager;

    private final Console console;

    public Info(CollectionManager collectionManager, Console console) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("Информация о текущей коллекции:");
        console.println("Тип: " + collectionManager.getCollectionType());
        if (collectionManager.getElementsType() != null) {
            console.println("Класс коллекции: " + collectionManager.getElementsType());
            console.println("Время инициализации: " + collectionManager.getInitializationDate());
        }
        console.println("Количество элементов: " + collectionManager.getCollectionSize());
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
