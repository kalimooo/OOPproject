package com.group23.app.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;

public class PlayingMenu extends JPanel {
        Color menuBackgroundColor = Color.RED;
        Color buttonColor = Color.decode("#767675");
        private JLabel helloLabel;
        private JButton quitButton; 

        static PlayingMenu playingMenu;
    // Konstruktor
    public PlayingMenu() {
        // Skapa en panel för hela menyn och sätt bakgrundsfärgen
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(menuBackgroundColor); // Funkar inte just nu
        
        helloLabel = new JLabel("PRESS SPACE TO START!");
        helloLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        helloLabel.setForeground(Color.black); // Ändra textfärgen till vit
        helloLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Lägg till en kant till vänster om texten
        add(helloLabel);
        setBounds(0,0,800,60);
        
        

        PlayingMenu.playingMenu = this;
    }
}
