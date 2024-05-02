package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;
import managers.DumpManager;

import java.io.IOException;


public class Save extends Command {
    private CollectionManager collectionManager;
    private DumpManager dumpManager;

    private final Console console;

    public Save(DumpManager dumpManager, CollectionManager collectionManager, Console console) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.dumpManager = dumpManager;
        this.console = console;
    }

    @Override
    public void execute() throws IOException {
        try {
            this.dumpManager.writeToJSON(this.collectionManager.getCollection());
            this.collectionManager.ChangeFlag();
            console.println("Коллекция успешно сохранена!");
        } catch (IOException e) {
            throw new IOException("Не удалось записать коллекцию в файл!");
        }
    }


    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
