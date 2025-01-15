package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreManager {
    private static final String HIGSCORE_FILE = "highscores.txt";
    private List<HighscoreEntry> highscores = new ArrayList<>();

    public void addHighscore(String playerName, int score){
        highscores.add(new HighscoreEntry(playerName, score));
        sortHighscores();
    }

    private void sortHighscores(){
        //sort methode der List Klasse lambda ausdruck zur compare methode der Integer Klasse
        highscores.sort((e1, e2) -> Integer.compare(e2.score, e1.score));
    }

    public void printHighscores(){
        System.out.println("------Highscores------");
        for(int i = 0; i < highscores.size(); i++){
            HighscoreEntry entry = highscores.get(i);
            System.out.println((i+1) + ". " + entry.playerName + ": " + entry.score + " Punkte");
        }
    }

    public void saveHighscores(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(HIGSCORE_FILE))){
            for (HighscoreEntry entry : highscores){
                writer.write(entry.playerName() + ":" +entry.score());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Fehler beim Speichern des Highscores" + e.getMessage());
        }
    }

    public void loadHighscores(){
        highscores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGSCORE_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(":");
                if (parts.length == 2){
                    highscores.add(new HighscoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("Fehler beim Laden des Highscores: " + e.getMessage());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Fehler beim Laden der Highscores: " + e.getMessage());
        }

        sortHighscores();
    }

    public List<HighscoreEntry> getHighscores() {
        return new ArrayList<>(highscores); // Schutz vor Änderungen
    }

    // Record-Klasse für Highscore-Einträge
    private record HighscoreEntry(String playerName, int score) {}
}
