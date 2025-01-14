package model;

public class QuestionFactory {
    public static Question createMultipleChoiceQuestion(String questionText, String[] options, String correctAnswer){
        return new MultipleChoiceQuestion(questionText, options, correctAnswer);
    }
}
