package model;

import java.util.List;

public class QuestionData {
    private String type;
    private String question;
    private List<String> options;
    private String correctAnswer;

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
