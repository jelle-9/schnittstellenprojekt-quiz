package model;

/**
Abstrakte Oberklasse der Fragen mit gemeinsamen Eigenschaften wie questionText und
 methoden wie checkAnswer
 */
public abstract class Question {
    protected String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public abstract boolean checkAnswer(String answer);

    public String getQuestionText() {
        return questionText;
    }

    public abstract String getQuestionType();
}
