package commands;

import Models.Vehicle;
import Models.builders.VehicleBuilder;
import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.IncorrectInputEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;

import java.util.NoSuchElementException;


public class UpdateID extends Command {
    private CollectionManager collectionManager;

    private Integer id;

    private final Console console;

    public UpdateID(CollectionManager collectionManager, Console console) {
        super("update_id", "{element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override

    public void execute() throws NoSuchElementException, CollectionisEmptyEX {
        if (this.collectionManager.getCollection().isEmpty()) throw new CollectionisEmptyEX("Коллекция пуста!");
        if (!this.collectionManager.containsId(id)) {
            throw new NoSuchElementException("Транспорта с таким id не существует!");
        }
        try {
            VehicleBuilder vehicleBuilder = new VehicleBuilder(console, collectionManager);
            Vehicle vehicleBuild = vehicleBuilder.build();
            collectionManager.removeById(id);
            vehicleBuild.setId(id);
            collectionManager.AddElementtoCollection(vehicleBuild);
            console.println("Данные транспорта успешно обновлены!");
        } catch (IncorrectInputEX e) {
            console.printError(e.getMessage());
        }

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

