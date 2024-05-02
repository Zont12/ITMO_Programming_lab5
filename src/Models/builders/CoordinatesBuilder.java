package Models.builders;

import Models.Coordinates;
import SystemTools.Console;
import exceptions.IncorrectInputEX;
import exceptions.MustBeNotEmptyEX;
import managers.InputManager;

import java.util.NoSuchElementException;

public class CoordinatesBuilder extends CollectionBuilder<Coordinates> {
    private final Console console;


    public CoordinatesBuilder(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() throws IncorrectInputEX {
        Coordinates coordinates = new Coordinates(requestX(), requestY());
        if (!coordinates.validate()) {
            throw new IncorrectInputEX("Число должно быть больше -296");

        }
        return coordinates;
    }

    public int requestX() {
        boolean filemode = InputManager.isFileMode();
        String input;
        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите координату X: ");
                }
                input = InputManager.getUserScanner().nextLine().trim();
                if (input.isEmpty()) throw new MustBeNotEmptyEX("Поле не может быть пустым");
                int x = Integer.parseInt(input);

                return x;

            } catch (MustBeNotEmptyEX e) {
                console.printError(e.getMessage());
            } catch (NumberFormatException e) {
                console.printError("Некорректный формат координаты. Введите число: ");
            } catch (NoSuchElementException e) {
                console.printError("Координата не распознана");
            } catch (IllegalStateException e) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
    }

    public float requestY() {
        boolean filemode = InputManager.isFileMode();
        String input;

        while (true) {
            try {
                if (!filemode) {
                    console.println("Введите координату Y: ");
                }
                input = InputManager.getUserScanner().nextLine().trim();
                if (input.isEmpty()) throw new MustBeNotEmptyEX("Поле не может быть пустым");
                float y = Float.parseFloat(input);

                return y;

            } catch (MustBeNotEmptyEX e) {
                console.printError(e.getMessage());
            } catch (NumberFormatException e) {
                console.printError("Некорректный формат координаты. Введите число: ");
            } catch (NoSuchElementException e) {
                console.printError("Координата не распознана");
            } catch (IllegalStateException e) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
    }
}
