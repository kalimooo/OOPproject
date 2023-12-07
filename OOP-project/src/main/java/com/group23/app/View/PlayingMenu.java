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

import com.group23.app.Model.Model;

public class PlayingMenu extends JPanel {
    Color menuBackgroundColor = Color.decode("#4f504e");
    Color buttonColor = Color.decode("#767675");
    private JLabel helloLabel;
    private JButton quitButton;

    static PlayingMenu playingMenu;

    // Konstruktor
    private PlayingMenu() {
        // Använd BorderLayout för att placera komponenter
        setLayout(new BorderLayout());

        // Sätt bakgrundsfärgen för hela menyn
        setBackground(menuBackgroundColor);

        helloLabel = new JLabel("00:00:01");
        helloLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        helloLabel.setForeground(Color.WHITE); // Ändra textfärgen till vit
        helloLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Lägg till en kant till vänster om texten

        // Align the label to the left
        helloLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        add(helloLabel);
        setBounds(0, 0, GameWindow.SCREEN_WIDTH, 60);

        add(helloLabel, BorderLayout.WEST); // Placera helloLabel längst till vänster

        quitButton = new JButton("QUIT");
        JPanel quitButtonPanel = new JPanel(new BorderLayout());
        quitButtonPanel.add(quitButton, BorderLayout.EAST);
        quitButtonPanel.setBorder(new EmptyBorder(0, 0, 0, 40));
        quitButtonPanel.setBackground(menuBackgroundColor);
        quitButton.setFocusable(false); // Förhindra att knappen tar emot fokus
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        quitButton.setBackground(buttonColor);
        quitButton.setForeground(Color.WHITE);
        add(quitButtonPanel, BorderLayout.EAST); // Placera quitButton längst till höger

        // ActionListener för quitbutton
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("USER PRESSED QUIT!");
                showQuitDialog();

            }
        });

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
        String message = "<html><font color='white'>Game over! Your score is: "
                /* //TODO: hämta tiden här */ + "</font></html>";

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
