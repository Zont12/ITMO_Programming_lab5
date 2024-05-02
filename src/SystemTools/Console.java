package SystemTools;

import Utility.ConsoleCommand;

import java.util.Scanner;


public class Console implements ConsoleCommand {
    public static Console CONSOLE;

    // сканер для обработки пользовательского ввода
    private Scanner scanner;

    public Console() {
    }

    // проверка наличия консоли

    // метод для выбора сканнера
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void println(Object object) {
        System.out.println(object);
    }

    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    @Override
    public void printError(Object object) {
        System.err.println("Ошибка:" + " " + object);
    }

}
