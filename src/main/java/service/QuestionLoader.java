package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Question;
import model.QuestionData;
import model.QuestionFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class QuestionLoader {

    public static List<Question> loadQuestions(String fileName) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {

            // Umwandeln des JSONs zu einer Liste von QuestionData
            Type questionDataType = new TypeToken<List<QuestionData>>() {}.getType();
            List<QuestionData> questionDataList = gson.fromJson(reader, questionDataType);

            List<Question> questions = new ArrayList<>();
            for (QuestionData data : questionDataList) {
                String type = data.getType();
                String question = data.getQuestion();
                Object correctAnswer;

                // Konvertiere correctAnswer basierend auf dem Fragetyp
                if (type.equalsIgnoreCase("true_false")) {
                    correctAnswer = Boolean.parseBoolean(data.getCorrectAnswer().trim().toLowerCase());
                    // Füge die Frage hinzu
                    questions.add(QuestionFactory.createQuestion(
                            type,
                            question,
                            correctAnswer // Direkt den Boolean übergeben
                    ));
                } else if (type.equalsIgnoreCase("multiple_choice")) {
                    String[] options = data.getOptions() != null ? data.getOptions().toArray(new String[0]) : new String[0];
                    correctAnswer = data.getCorrectAnswer(); // Die richtige Antwort als String

                    // MultipleChoice Frage erstellen
                    questions.add(QuestionFactory.createQuestion(
                            type,
                            question,
                            options,  // Optionen als String[]
                            correctAnswer // Die richtige Antwort als String
                    ));
                }
            }

            return questions;
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Fragen: " + e.getMessage());
            throw e; // Ausnahme weiterwerfen, um den Fehler nach außen sichtbar zu machen
        }
    }
}