package commands;

import Helpers.QuestionAsker;
import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

public class Exit extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Exit(Console console, CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (this.collectionManager.WasNotChanged()) {
            console.println("Последние изменения в коллекции не будут сохранены!");
        }
        QuestionAsker questionAsker = new QuestionAsker("Вы уверены что хотите выйти?", console);
        if (questionAsker.ask()) {
            System.exit(0);
        }
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}