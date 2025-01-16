package model;

public class QuestionFactory {

    //varargs (...) erlauben, dass die methode verschiedene anzahlen an argumenten erhalten kann
    public static Question createQuestion(String type, String text, Object... args){
        switch (type.toLowerCase()){
            case "multiple_choice":
                if (args.length < 2 || !(args[0] instanceof String[]) || !(args[1] instanceof String))
                    throw new IllegalArgumentException("Ungültige Argumente für Multiple-Choice-Frage");
                return new MultipleChoiceQuestion(text, (String[]) args[0], (String) args[1]);

            case "true_false":
                if (args.length < 1 || !(args[0] instanceof Boolean)) {
                    throw new IllegalArgumentException("Ungültige Argumente für True/False-Frage");
                }
                return new TrueFalseQuestion(text, (Boolean) args[0]);

            default:
                throw new IllegalArgumentException("Unbekannter Fragetyp: " + type);
        }
    }


//    public static Question createMultipleChoiceQuestion(String questionText, String[] options, String correctAnswer){
//        return new MultipleChoiceQuestion(questionText, options, correctAnswer);
//    }
}
