package managers;

import java.util.Scanner;

public class InputManager {

    private static boolean fileMode = false;

    private static Scanner scanner;

    // возвращает сканер пользователя
    public static Scanner getUserScanner() {
        return scanner;
    }

    // задает сканер пользователю
    public static void setUserScanner(Scanner scanner) {
        InputManager.scanner = scanner;
    }


    // метод для чтения next line
    public static String readLine() {
        return scanner.nextLine();
    }

    // указывает это скрипт или нет
    public static boolean isFileMode() {
        return fileMode;
    }

    public static void ChangeFileMode() {
        fileMode = true;
    }


}
