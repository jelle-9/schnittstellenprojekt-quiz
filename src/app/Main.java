package app;

import model.QuestionFactory;
import observer.*;
import service.QuizSession;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        QuizNotifier notifier = new QuizNotifier();

        QuizSession session = new QuizSession();
        session.addObserver(notifier);

        //Test-Fragen
        //Multiple-Choice
        session.addQuestion(QuestionFactory.createQuestion(
                "multiple_choice",
                "Was ist die Standard-Datenstruktur, die in Java verwendet wird, um eine geordnete Sammlung von Elementen zu speichern?",
                List.of("List", "Set", "Queue", "Map"),
                "1"
        ));

        //True-False
        session.addQuestion(QuestionFactory.createQuestion(
                "true_false",
                "Java ist eine Programmiersprache.",
                true
        ));

        session.start();
    }
}
