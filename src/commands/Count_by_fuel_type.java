package commands;

import Models.FuelType;
import Models.Vehicle;
import SystemTools.Console;
import exceptions.CollectionisEmptyEX;
import exceptions.WrongCommandArgsEX;
import managers.CollectionManager;


public class Count_by_fuel_type extends Command {
    private CollectionManager collectionManager;

    private FuelType fuelType;


    private final Console console;

    public Count_by_fuel_type(CollectionManager collectionManager, Console console) {
        super("count_by_fuel_type", "fuelType", "вывести количество элементов, значение поля fuelType которых равно заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute() throws CollectionisEmptyEX {
        int count = 0;
        if (this.collectionManager.getCollection().isEmpty()) {
            throw new CollectionisEmptyEX("Коллекция пуста!");
        }
        for (Vehicle vehicle : collectionManager.getCollection()) {
            if (fuelType != null && vehicle.getFuelType() == fuelType) {
                count++;
            } else {
                count += 0;
            }
        }
        if (count > 0) {
            console.println("Количество транспортов с типом топлива " + fuelType + " = " + count);
        }
    }


    @Override
    public void initCommandArgs(String[] args) throws WrongCommandArgsEX {
        if (args.length != 1) throw new WrongCommandArgsEX("У данной команды должен быть 1 аргумент");
        this.fuelType = FuelType.fromString(args[0]);
    }


}