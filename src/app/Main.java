package app;

import model.QuestionFactory;
import observer.*;
import service.QuizSession;

public class Main {
    public static void main(String[] args) {

        QuizNotifier notifier = new QuizNotifier();

        QuizSession session = new QuizSession();
        session.addObserver(notifier);

        //Test-Fragen
        session.addQuestion(QuestionFactory.createMultipleChoiceQuestion(
                "Was ist die Standard-Datenstruktur, die in Java verwendet wird, um eine geordnete Sammlung von Elementen zu speichern?",
                new String[]{"List", "Set", "Queue", "Map"},
                "1"
        ));
        session.addQuestion(QuestionFactory.createMultipleChoiceQuestion(
                "Welches SQL-Statement wird verwendet, um Daten in eine Tabelle einzufügen?",
                new String[]{"SELECT", "INSERT", "DELETE", "UPDATE"},
                "2"
        ));

        session.start();
    }
}
