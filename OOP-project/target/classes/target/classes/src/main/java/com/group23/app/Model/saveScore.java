package com.group23.app.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class saveScore {

      public static void saveScoreToFile(String scoreEntry) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt", true))) {
            // Skriv poängen till filen
            writer.write(scoreEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
