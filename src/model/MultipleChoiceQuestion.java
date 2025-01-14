package model;


public class MultipleChoiceQuestion extends Question {
    private String[] options; // möglichen Antworten
    private String correctAnswer; // Antwort

    public MultipleChoiceQuestion(String questionText, String[] options, String correctAnswer) {
        super(questionText);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    public String[] getOptions() {
        return options;
    }
}
