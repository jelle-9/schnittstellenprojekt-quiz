package service;

import model.MultipleChoiceQuestion;
import model.Question;
import model.TrueFalseQuestion;
import observer.QuizObserver;

import java.util.ArrayList;
import java.util.List;

public class QuizSession {
    private List<QuizObserver> observers = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private String playerName;
    private HighscoreManager highscoreManager;

    public QuizSession(String playerName, HighscoreManager highscoreManager) {
        this.playerName = playerName;
        this.highscoreManager = highscoreManager;
        this.highscoreManager.loadHighscores();
    }

    public void addObserver(QuizObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (QuizObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {

        // Spielername fehlt - Abbruch
        if (playerName == null || playerName.isEmpty()) {
            System.out.println("Spielername ist nicht gesetzt! Bitte setzen Sie den Spielernamen vor dem Start.");
            return;
        }

        notifyObservers("Das Quiz wurde gestartet!");
        System.out.println("Willkommen, " + playerName + "! Gib 'q' ein, um das Quiz jederzeit abzubrechen.\n");

        for (Question question : questions) {
            System.out.println("Frage: " + question.getQuestionText());

            if (question instanceof MultipleChoiceQuestion) {
                String[] options = ((MultipleChoiceQuestion) question).getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }
            } else if (question instanceof TrueFalseQuestion) {
                System.out.println("Antwortmöglichkeiten: (w)ahr/(t)rue/1 -oder- (f)alsch/false/0");
            }

            System.out.println("Deine Antwort: ");
            String userAnswer = new java.util.Scanner(System.in).nextLine().trim();

            // Überprüfung auf Abbruch
            if (userAnswer.equalsIgnoreCase("q")) {
                System.out.println("Das Quiz wurde abgebrochen.");
                notifyObservers("Das Quiz wurde vom Benutzer abgebrochen!");
                break;
            }

            if (question.checkAnswer(userAnswer)) {
                score++;
                notifyObservers("Richtig! Aktueller Punktestand: " + score);
            } else {
                notifyObservers("Falsch!");
            }
        }

        notifyObservers("Quiz beendet! Endpunktestand: " + score);
        highscoreManager.addHighscore(playerName, score);
        highscoreManager.printHighscores();
    }
}