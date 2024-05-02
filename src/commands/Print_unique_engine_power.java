package commands;

import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class Print_unique_engine_power extends Command {
    private CollectionManager collectionManager;

    private final Console console;

    public Print_unique_engine_power(CollectionManager collectionManager, Console console) {
        super("print_unique_engine_power", "вывести уникальные значения поля enginePower всех элементов в коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() throws CollectionisEmptyEX {
        if (this.collectionManager.getCollection().isEmpty()) throw new CollectionisEmptyEX("Коллекция пуста!");
        String view = this.collectionManager.get_unique_engine_power().toString();
        view = view.replace("[", "{").replace("]", "}");
        console.println(view);
        console.println("Команда успешно выполнена!");
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}


// Print_unique_engine_power