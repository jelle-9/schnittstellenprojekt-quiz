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
        answer = answer.trim().toLowerCase();

        if (answer.equals("true") || answer.equals("t") || answer.equals("whar") || answer.equals("1") || answer.equals("w")){
            return correctAnswer;
        }

        if (answer.equals("false") || answer.equals("f") || answer.equals("falsch") || answer.equals("0")){
            return !correctAnswer;
        }


        return Boolean.parseBoolean(answer.toLowerCase()) == correctAnswer;
    }

    @Override
    public String getQuestionType() {
        return "true_false";
    }
}