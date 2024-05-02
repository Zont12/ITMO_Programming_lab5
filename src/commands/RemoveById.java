package commands;

import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

import java.util.NoSuchElementException;

public class RemoveById extends Command {
    private CollectionManager collectionManager;

    private Integer id;

    private final Console console;

    public RemoveById(CollectionManager collectionManager, Console console) {
        super("remove_by_id", "id", "Удалить элемент коллекции по id");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() throws NoSuchElementException, CollectionisEmptyEX {
        if (this.collectionManager.getCollection().isEmpty()) throw new CollectionisEmptyEX("Коллекция пуста!");
        if (!this.collectionManager.containsId(id)) {
            throw new NoSuchElementException("Транспорта с таким id не существует!");
        }
        this.collectionManager.removeById(id);
        console.println("Транспорт был успешно удален из коллекции! ");

    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 1) throw new WrongCommandArgsEX("У данной команды должен быть 1 аргумент");
        try {
            this.id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new WrongCommandArgsEX("Аргумент должен быть целым числом");
        }

    }
}

