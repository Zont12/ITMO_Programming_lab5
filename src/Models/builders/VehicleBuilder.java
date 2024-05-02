package Models.builders;

import Models.Coordinates;
import Models.FuelType;
import Models.Vehicle;
import SystemTools.Console;
import exceptions.GreaterThanZeroEX;
import exceptions.IncorrectInputEX;
import exceptions.MustBeNotEmptyEX;
import managers.CollectionManager;
import managers.InputManager;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


public class VehicleBuilder extends CollectionBuilder<Vehicle> {
    private final Console console;
    private Integer id;
    private CollectionManager collectionManager;


    public VehicleBuilder(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Vehicle build() throws IncorrectInputEX {
        Vehicle vehicle = new Vehicle(
                id = collectionManager.GemerateIds(),
                Requestname(),
                Requestcoordinates(),
                LocalDateTime.now(),
                Requestenginepower(),
                Requestnubmerofwheels(),
                Requestcapacity(),
                RequestFuelType());
        if (!vehicle.validate()) throw new IllegalAccessError();
        return vehicle;
    }

    private String Requestname() {
        String name;
        boolean filemode = InputManager.isFileMode();
        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите имя транспорта: ");
                }
                name = InputManager.getUserScanner().nextLine().strip();
                if (name.isEmpty()) throw new MustBeNotEmptyEX("");
                break;
            } catch (MustBeNotEmptyEX e) {
                console.printError("Поле не может быть пустым");
            } catch (NoSuchElementException e) {
                console.printError("Имя не распознано! Введите имя транспорта снова:");
            } catch (IllegalStateException e) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    private Coordinates Requestcoordinates() throws IncorrectInputEX {
        return new CoordinatesBuilder(console).build();
    }

    private Integer Requestenginepower() {
        Integer enginepower;
        boolean filemode = InputManager.isFileMode();
        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите мощность двигателя ");
                }
                String input = InputManager.getUserScanner().nextLine().strip();
                if (input.isEmpty()) throw new MustBeNotEmptyEX("Поле не может быть пустым");
                enginepower = Integer.parseInt(input);
                if (enginepower <= 0)
                    throw new GreaterThanZeroEX("Мощность двигателя должна быть больше 0. Введите мощность двигателя заново: ");
                break;
            } catch (MustBeNotEmptyEX e) {
                console.printError(e.getMessage());
            } catch (NumberFormatException e) {
                console.printError("Некорректный формат мощности. Введите мощность двигателя; ");
            } catch (NoSuchElementException e) {
                console.printError("Мощность двигателя не распознано! Введите мощность двигателя снова: ");
            } catch (GreaterThanZeroEX e) {
                console.printError(e.getMessage());
            } catch (IllegalStateException e) {
                console.printError("Произошла непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return enginepower;
    }

    private Integer Requestnubmerofwheels() {
        Integer numberofwheels;
        boolean filemode = InputManager.isFileMode();
        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите количество колес транспорта:");
                }
                String input = InputManager.getUserScanner().nextLine().strip();
                if (input.isEmpty()) throw new MustBeNotEmptyEX("Поле не может быть пустым");
                numberofwheels = Integer.parseInt(input);
                if (numberofwheels <= 0)
                    throw new GreaterThanZeroEX("Количество колес транспорта должно быть больше 0. Введите количество колес транспорта заново: ");
                break;
            } catch (MustBeNotEmptyEX e) {
                console.printError(e.getMessage());
            } catch (NumberFormatException e) {
                console.printError("Некорректный формат количества колес транспорта. Введите количество колес транспорта заново; ");
            } catch (NoSuchElementException e) {
                console.printError("Количество колес транспорта не распознано! Введите количество колес транспорта снова: ");
            } catch (GreaterThanZeroEX e) {
                console.printError(e.getMessage());
            } catch (IllegalStateException e) {
                console.printError("Произошла непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return numberofwheels;
    }

    private long Requestcapacity() {
        long capacity;
        boolean filemode = InputManager.isFileMode();
        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите количество мест транспорта:");
                }
                String input = InputManager.getUserScanner().nextLine().strip();
                capacity = Long.parseLong(input);
                if (capacity <= 0)
                    throw new GreaterThanZeroEX("Вместимость транспорта должна быть больше 0. Введите вместимость транспорта заново: ");
                break;
            } catch (NumberFormatException e) {
                console.printError("Некорректный формат вместимости транспорта. Введите вместимость транспорта заново; ");
            } catch (NoSuchElementException e) {
                console.printError("Вместимость транспорта не распознано! Введите вместимость транспорта заново: ");
            } catch (GreaterThanZeroEX e) {
                console.printError(e.getMessage());
            } catch (IllegalStateException e) {
                console.printError("Произошла непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return capacity;
    }

    private FuelType RequestFuelType() {
        return new FuelTypebuilder(console).build();
    }
}
