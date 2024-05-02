package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;


public class Show extends Command {
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() {
        if (this.collectionManager.getCollection().isEmpty()) {
            console.println("Коллекция пуста");
        }
        this.collectionManager.getCollection()
                .forEach(Vehicle -> console.println(Vehicle));
    }


    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
