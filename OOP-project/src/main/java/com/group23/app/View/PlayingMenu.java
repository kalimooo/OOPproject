package com.group23.app.View;
import com.group23.app.Model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.group23.app.Model.Entity;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


public class PlayingMenu extends JPanel {
    Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
    Color menuBackgroundColor = Color.decode("#4f504e");
    Color buttonColor = Color.decode("#767675");
    private JLabel scoreLabel;
    private JButton quitButton;
    private JButton tutorialButton;
    public JButton muteButton;

    static PlayingMenu playingMenu;

    private PlayingMenu() {
        setLayout(new BorderLayout());
        setBackground(menuBackgroundColor);

        scoreLabel = new JLabel("");
        scoreLabel.setFont(menuFont);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        scoreLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        add(scoreLabel, BorderLayout.WEST);

        quitButton = new JButton("[Q] QUIT");
        quitButton.setFocusable(false);
        quitButton.setFont(menuFont);
        quitButton.setBackground(buttonColor);
        quitButton.setForeground(Color.WHITE);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuitDialog();
            }
        });

        tutorialButton = new JButton("[T] TUTORIAL");
        tutorialButton.setFocusable(false);
        tutorialButton.setFont(menuFont);
        tutorialButton.setBackground(buttonColor);
        tutorialButton.setForeground(Color.WHITE);

        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("USER PRESSED TUTORIAL!");
            }
        });

        muteButton = new JButton("[M] MUTE");
        muteButton.setFocusable(false);
        muteButton.setFont(menuFont);
        muteButton.setBackground(buttonColor);
        muteButton.setForeground(Color.WHITE);

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("USER PRESSED MUTE!");
                if (GameWindow.getGameWindow().backgroundMusic.isRunning()) {
                    GameWindow.getGameWindow().stopBackgroundMusic();
                    muteButton.setText("[M] Unmute");
                } else {
                    GameWindow.getGameWindow().playBackgroundMusic();
                    muteButton.setText("[M] Mute");
                }
            }
        });

        // Use FlowLayout for the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(menuBackgroundColor);
        buttonPanel.add(muteButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(quitButton);
        buttonPanel.setFocusable(false);

        add(buttonPanel, BorderLayout.EAST);

        setBounds(0, 0, GameWindow.SCREEN_WIDTH, 60);

        PlayingMenu.playingMenu = this;
    }

    public static PlayingMenu getPlayingMenu() {
        if (PlayingMenu.playingMenu == null) {
            return new PlayingMenu();
        }
        return PlayingMenu.playingMenu;
    }

    public void updateTime() {
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds(); // Get the elapsed time TODO needs to be changed,
                                                                       // breaks MVC
        this.scoreLabel.setText(elapsedTime + " points"); // Update the score
    }

    public void showQuitDialog() {
        UIManager.put("OptionPane.background", menuBackgroundColor);
        UIManager.put("Panel.background", menuBackgroundColor);
        UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);
    
        // Hämta det aktuella poängen
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds();
        String currentScore = elapsedTime + " points";
    
        // Skapar lista över highscores
        List<ScoreEntry> highScores = getHighScoreData();
    
        // Sortera i fallande ordning (Störst --> Minst)
        Collections.sort(highScores, Collections.reverseOrder());
    
        // Visa de 10 bästa highscoren inklusive den aktuella poängen
        StringBuilder highScoreMessage = new StringBuilder("<html><font color='white'>Top 10 Highscores:<br><br>");
    
        int count = 0;
        for (ScoreEntry entry : highScores) {
            if (count >= 10) {
                break;
            }
            // Lägger till highscore med radbrytning
            highScoreMessage.append(entry.toString()).append("<br>");
            count++;
        }
    
        // Lägg till den aktuella poängen i meddelandet
        highScoreMessage.append("<br>Your score: ").append(currentScore).append("</font></html>");
    
        // Visa dialogrutan med highscore-meddelandet
        JOptionPane.showMessageDialog(this, highScoreMessage.toString(), "Highscores", JOptionPane.INFORMATION_MESSAGE);
    
        Object[] options = { "Restart Game", "Go to Menu" };
        int result = JOptionPane.showOptionDialog(
                this,
                "<html><font color='white'>Do you want to quit?</font></html>",
                "Game over!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    
        // Handle the user's choice
        switch (result) {
            case JOptionPane.YES_OPTION:
                System.out.println("User pressed Restart Game");
                Model.getModel().resetGame();
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("User pressed Go to Menu");
                // Perform actions for going to the menu
                break;
            default:
                // User closed the dialog without making a choice
                break;
        }
    
        // Låt användaren ange sitt namn
        String playerName = JOptionPane.showInputDialog(this,
                "<html><font color ='white'> Enter your name:</font></html>");
    
        // Kontrollera om spelarnamnet är null eller tomt
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Skapa en sträng för att spara i filen
            String scoreEntry = currentScore + ";" + playerName;
    
            // Spara poängen i filen
            saveScoreToFile(scoreEntry);
        } else {
            // Användaren har inte angett ett giltigt namn, ge felmeddelande
            JOptionPane.showMessageDialog(this, "Invalid name. Score not saved.", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    

    private List<ScoreEntry> getHighScoreData() {
        List<ScoreEntry> highScores = new ArrayList<>();
    
        // Läs av högsta poäng och spelarnamn från filen
        try (BufferedReader reader = new BufferedReader(new FileReader("OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String scoreString = parts[0].trim().replaceAll("[^\\d]", "");
                    long score = Long.parseLong(scoreString);
                    highScores.add(new ScoreEntry(score, parts[1].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return highScores;
    }

    private void saveScoreToFile(String scoreEntry) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt", true))) {
            // Skriv poängen till filen
            writer.write(scoreEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A nested static class representing a ScoreEntry. 
    // Implements Comparable to allow for sorting based on the score.
    private static class ScoreEntry implements Comparable<ScoreEntry> {
        private final long score;
        private final String playerName;

        public ScoreEntry(long score, String playerName) {
            this.score = score;
            this.playerName = playerName;
        }

        @Override
        public int compareTo(ScoreEntry other) {
            return Long.compare(this.score, other.score);
        }

        @Override
        public String toString() {
            return playerName + ": " + score + " points";
        }
    }

}