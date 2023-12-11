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
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
                System.out.println("USER PRESSED QUIT!");
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
        // Get the elapsed time TODO needs to be changed, breaks MVC
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds();
        // Update the score
        this.scoreLabel.setText(elapsedTime + " points");
    }

    public void showQuitDialog() {
        UIManager.put("OptionPane.background", menuBackgroundColor);
        UIManager.put("Panel.background", menuBackgroundColor);
        UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);

        // Get the current score
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds();
        String currentScore = elapsedTime + " points";

        // Create list of highscores
        List<ScoreEntry> highScores = getHighScoreData();

        // Sort from Largest --> Smallest score
        Collections.sort(highScores, Collections.reverseOrder());

        // Show the 10 highest highscoers and the current score

        StringBuilder highScoreMessage = new StringBuilder("<html><font color='white'>Top 10 Highscores:<br><br>");

        int count = 0;
        for (ScoreEntry entry : highScores) {
            if (count >= 10) {
                break;
            }
            // Add highscore with new line
            highScoreMessage.append(entry.toString()).append("<br>");
            count++;
        }

        // Show current score
        highScoreMessage.append("<br>Your score: ").append(currentScore).append("</font></html>");

        // Show dialog with high score message
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
                // Perform actions for restarting the game
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("User pressed Go to Menu");
                // Perform actions for going to the menu
                break;
            default:
                // User closed the dialog without making a choice
                break;
        }

        // To allow user to enter its name
        String playerName = JOptionPane.showInputDialog(this,
                "<html><font color ='white'> Enter your name:</font></html>");

        // Check if player name is empty
        if (playerName != null && !playerName.trim().isEmpty()) {
            // Create string to save in file
            String scoreEntry = currentScore + ";" + playerName;

            // Save score to our file
            saveScoreToFile(scoreEntry);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid name. Score not saved.", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private List<ScoreEntry> getHighScoreData() {
        List<ScoreEntry> highScores = new ArrayList<>();

        // Reads highest points from file
        try (BufferedReader reader = new BufferedReader(
                new FileReader("OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt"))) {
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
            // Write points to file
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