package commands;

import Helpers.VehicleEnginePowerComparator;
import Models.Vehicle;
import Models.builders.VehicleBuilder;
import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.IncorrectInputEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;


public class Execute_add_if_max extends Command {
    private CollectionManager collectionManager;
    private final Console console;

    public Execute_add_if_max(CollectionManager collectionManager, Console console) {
        super("execute_add_if_max", "{element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() throws CollectionisEmptyEX {
        if (this.collectionManager.getCollection().isEmpty()) throw new CollectionisEmptyEX("Коллекция пуста!");
        VehicleBuilder vehicleBuilder = new VehicleBuilder(console, collectionManager);
        try {
            Vehicle vehicleBuild = vehicleBuilder.build();
            VehicleEnginePowerComparator comparator = new VehicleEnginePowerComparator();
            if (collectionManager.getCollection().isEmpty() || collectionManager.getCollection().stream().allMatch(vehicle -> comparator.compare(vehicle, vehicleBuild) < 0)) {
                collectionManager.AddElementtoCollection(vehicleBuild);
                console.println("Транспорт успешно добавлен");
            } else {
                console.printError("Новый транспорт имеет недостаточно мощный двигатель!");
            }
        } catch (IncorrectInputEX e) {
            console.printError(e.getMessage());
        }
    }

    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 0) {
            throw new WrongCommandArgsEX("У данной команды не может быть аргументов");
        }
    }
}



