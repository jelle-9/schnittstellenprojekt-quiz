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

    public void addObserver(QuizObserver observer){
        observers.add(observer);
    }

    private void notifyObservers(String message){
        for(QuizObserver observer : observers){
            observer.update(message);
        }
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void start(){
        notifyObservers("Das Quiz wurde gestartet!");
        for(Question question : questions){
            System.out.println("Frage: " + question.getQuestionText());
            if (question instanceof MultipleChoiceQuestion) {
                String[] options = ((MultipleChoiceQuestion) question).getOptions();
                for(int i = 0; i < options.length; i++){
                    System.out.println((i+1) + ". " + options[i]);
                }
            }else if(question instanceof TrueFalseQuestion) {
                System.out.println("Antwortmglichkeiten: wahr (true) oder falsch (false)");
            }

            System.out.println("Deine Antwort: ");
            String userAnswer = new java.util.Scanner(System.in).nextLine();

            if (question.checkAnswer(userAnswer)){
                score++;
                notifyObservers("Richtig! Aktueller Punktestand: " + score);
            } else {
                notifyObservers("Falsch!");
            }
        }
        notifyObservers("Quiz beendet! Endpunktestand: "+ score);
    }
}
