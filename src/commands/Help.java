package commands;

import SystemTools.Console;
import exceptions.WrongCommandArgsEX;
import managers.CommandManager;

public class Help extends Command {
    private final CommandManager commandManager;

    private final Console console;

    public Help(CommandManager commandManager, Console console) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute() {
        this.commandManager.getCommandList().forEach(command -> console.println(command));

    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}
