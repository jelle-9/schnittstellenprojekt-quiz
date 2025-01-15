package model;

public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (answer == null) return false;
        return Boolean.parseBoolean(answer.toLowerCase()) == correctAnswer;
    }

    @Override
    public String getQuestionType() {
        return "true_false";
    }
}