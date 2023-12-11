package com.group23.app.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import com.group23.app.Model.Model;

public class PlayingMenu extends JPanel {
    Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
    Color menuBackgroundColor = Color.decode("#4f504e");
    Color buttonColor = Color.decode("#767675");
    private JLabel helloLabel;
    private JButton quitButton;
    private JButton tutorialButton;

    static PlayingMenu playingMenu;

    private PlayingMenu() {
        setLayout(new BorderLayout());
        setBackground(menuBackgroundColor);

        helloLabel = new JLabel("00:00:01");
        helloLabel.setFont(menuFont);
        helloLabel.setForeground(Color.WHITE);
        helloLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        helloLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        add(helloLabel, BorderLayout.WEST);

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
                // Add your code to handle the tutorial button press (open tutorial, etc.)
            }
        });

        // Use FlowLayout for the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(menuBackgroundColor);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(quitButton);

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
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds(); // Get the elapsed time TODO needs to be changed, breaks MVC

        long minutes = elapsedTime / 60; // Convert to minutes
        long hours = minutes / 60; // Convert to hours

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes % 60, elapsedTime % 60); // Format the
                                                                                                       // time to
                                                                                                       // 00:00:00
                                                                                                       // format

        this.helloLabel.setText(formattedTime); // Update the helloLabel
    }

    private void showQuitDialog() {
        UIManager.put("OptionPane.background", menuBackgroundColor);
        UIManager.put("Panel.background", menuBackgroundColor);
        UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);

        // Använd HTML-formatering för att ställa in textfärgen till vit
        String message = "<html><font color='white'>Game over! Your score is: " + Model.getModel().getElapsedTimeInSeconds() + "</font></html>";

        JOptionPane.showMessageDialog(this, message, "Game Over!", JOptionPane.INFORMATION_MESSAGE);

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
    }

}
