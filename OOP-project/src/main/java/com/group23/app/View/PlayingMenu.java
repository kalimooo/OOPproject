package com.group23.app.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

        helloLabel = new JLabel("Carlbanan");
        helloLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        helloLabel.setForeground(Color.WHITE); // Ändra textfärgen till vit
        helloLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Lägg till en kant till vänster om texten
        
        // Align the label to the left
        helloLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        add(helloLabel);
        setBounds(0,0,800,60);
        
        
        add(helloLabel, BorderLayout.WEST); // Placera helloLabel längst till vänster

        quitButton = new JButton("QUIT");
        JPanel quitButtonPanel = new JPanel(new BorderLayout());
        quitButtonPanel.add(quitButton, BorderLayout.EAST);
        quitButtonPanel.setBorder(new EmptyBorder(0,0,0,40));
        quitButtonPanel.setBackground(menuBackgroundColor);
        quitButton.setFocusable(false); // Förhindra att knappen tar emot fokus
        add(quitButtonPanel, BorderLayout.EAST); // Placera quitButton längst till höger

        // ActionListener för quitbutton
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("USER PRESSED QUIT!");
            }
        });

        setBounds(0, 0, 800, 60);

        PlayingMenu.playingMenu = this;
    }

    public static PlayingMenu getPlayingMenu() {
        if (PlayingMenu.playingMenu == null) {
            return new PlayingMenu();
        }
        return PlayingMenu.playingMenu;
    }

    public void updateTime() {
        long elapsedTime = Model.getModel().getElapsedTimeInSeconds(); // Get the elapsed time
        String gameWatch = Long.toString(elapsedTime);
        this.helloLabel.setText(gameWatch); // Update the helloLabel
    }
}
