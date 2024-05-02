package Models.builders;

import Helpers.QuestionAsker;
import Models.FuelType;
import SystemTools.Console;
import managers.InputManager;

import java.util.NoSuchElementException;

import static managers.InputManager.isFileMode;

public class FuelTypebuilder extends CollectionBuilder<FuelType> {
    private final Console console;

    public FuelTypebuilder(Console console) {
        this.console = console;
    }

    @Override
    public FuelType build() {
        String input;
        FuelType fuelType;
        boolean filemode = isFileMode();
        while (true) {
            try {
                if (!filemode ) {
                    console.println("У вашего транспорта есть топливо?");
                    }
                String userAnswer = InputManager.readLine().trim().toLowerCase();

                if (userAnswer.equals("yes")) {
                    if (!filemode) {
                        console.println("Выберете тип топлива транспорта: " + FuelType.names());
                    }
                    input = InputManager.getUserScanner().nextLine().trim();
                    fuelType = FuelType.valueOf(input.toUpperCase());
                    break;
                }
                else if (userAnswer.equals("no")) {
                    fuelType = null;
                    break;
                }
                console.println("Пожалуйста, введите 'yes' или 'no'");
            } catch (IllegalArgumentException exception) {
                console.printError("Такого типа топлива не существует!");
            } catch (NoSuchElementException exception) {
                console.printError("У каждого транспорта должно быть топливо!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return fuelType;
    }
}
