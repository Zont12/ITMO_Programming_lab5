package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;


public class Min_By_Number_Of_Wheels extends Command {
    private CollectionManager collectionManager;
    private final Console console;

    public Min_By_Number_Of_Wheels(CollectionManager collectionManager, Console console) {
        super("min_by_number_of_wheels", "вывести любой объект из коллекции, значение поля numberOfWheels которого является минимальным");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() {
        if (this.collectionManager.getCollection().isEmpty()) {
            console.println("Коллекция пуста");
            return;
        }
        console.println(this.collectionManager.get_min_by_number_of_wheels());
    }


    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}

