package model;

public class MultipleChoiceQuestion extends Question {
    private String[] options; // mögliche Antworten
    private String correctAnswer; // Index der richtigen Antwort als String (z.B. "0" für die erste Antwort)

    public MultipleChoiceQuestion(String questionText, String[] options, String correctAnswer) {
        super(questionText);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        try {
            // Benutzerantwort als Integer parsen und 1 abziehen, um den Index zu erhalten
            int selectedOption = Integer.parseInt(answer.trim()) - 1;

            // Prüfen, ob die gewählte Option im Bereich der Optionen liegt
            if (selectedOption >= 0 && selectedOption < options.length) {
                // Vergleiche den Index der gewählten Option mit der richtigen Antwort (correctAnswer ist ein String, der die Indexnummer speichert)
                return selectedOption == Integer.parseInt(correctAnswer);
            } else {
                return false; // Ungültiger Index
            }
        } catch (NumberFormatException e) {
            return false; // Falls die Eingabe keine Zahl war
        }
    }

    @Override
    public String getQuestionType() {
        return "multiple_choice";
    }

    public String[] getOptions() {
        return options;
    }
}