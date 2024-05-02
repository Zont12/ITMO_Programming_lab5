package Utility;

public interface ConsoleCommand {

    void print(Object object); // вывод строки без \n

    void println(Object object); // вывод строки с \n


    void printError(Object obj); // метод для возвращения ошибок

}
