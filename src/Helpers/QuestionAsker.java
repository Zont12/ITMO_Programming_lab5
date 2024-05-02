package Helpers;

import SystemTools.Console;
import managers.InputManager;

public class QuestionAsker {

    String question;
    Console console;


    public QuestionAsker(String question, Console console) {
        this.question = question;
        this.console = console;
    }

    public boolean ask() {
        if (InputManager.isFileMode()) return false;
        console.println(question);
        while (true) {
            String userAnswer = InputManager.readLine().trim().toLowerCase();
            if (userAnswer.equals("yes")) {
                return true;
            }
            if (userAnswer.equals("no")) {
                return false;
            }
            console.println("Пожалуйста, введите 'yes' или 'no'");
        }
    }
}