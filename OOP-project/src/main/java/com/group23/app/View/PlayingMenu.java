package com.group23.app.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PlayingMenu extends JPanel {
    Color menuBackgroundColor = Color.decode("#4f504e");
    Color buttonColor = Color.decode("#767675");
    private JLabel helloLabel;
    private JButton quitButton; 

    static PlayingMenu playingMenu;

    // Konstruktor
    public PlayingMenu() {
        // Använd BorderLayout för att placera komponenter
        setLayout(new BorderLayout());
        
        // Sätt bakgrundsfärgen för hela menyn
        setBackground(menuBackgroundColor);

        helloLabel = new JLabel("PRESS SPACE TO START!");
        helloLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        helloLabel.setForeground(Color.black); // Ändra textfärgen till vit
        helloLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Lägg till en kant till vänster om texten
        add(helloLabel, BorderLayout.WEST); // Placera helloLabel längst till vänster

        quitButton = new JButton("QUIT");
        quitButton.setFocusable(false); // Förhindra att knappen tar emot fokus
        quitButton.setMargin(new Insets(0, 20, 0, 20)); // Lägg till en kant till höger om knappen
        add(quitButton, BorderLayout.EAST); // Placera quitButton längst till höger

        setBounds(0, 0, 800, 60);

        PlayingMenu.playingMenu = this;
    }
}
