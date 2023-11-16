package com.group23.app.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayingField extends JPanel{
     
    //static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    //static final int fieldHeight = GameWindow.SCREEN_HEIGHT;
    static final int fieldWidth = 800;
    static final int fieldHeight = 500;
    
    static JLabel playerChar;
    static ArrayList<Laser> lasers = new ArrayList<Laser>();

    static PlayingField playingField;

    private PlayingField() {
        super(null);


        playerChar = new JLabel();
        playerChar.setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png",50,50));
        playerChar.setBounds(fieldWidth/2 - 30, fieldHeight/2 - 30, playerChar.getIcon().getIconWidth(), playerChar.getIcon().getIconHeight());
        add(playerChar);

        lasers.add(new Laser(60, 60, "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/Blue.png", Color.blue));

        for (Laser laser : lasers) {
            add(laser);
        }

        setBackground(Color.BLACK);
        setBounds(0,0, fieldWidth, fieldHeight);
        PlayingField.playingField = this;
    }

    public static PlayingField getPlayingField() {
        if (PlayingField.playingField == null) {
            return new PlayingField();
        }
        return PlayingField.playingField;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Laser laser : lasers) {
            g2.setColor(laser.getColor());
            g2.setStroke(new BasicStroke(7));
            g2.drawLine(laser.getEX() - 80, laser.getEY() - 80, laser.getEX(), laser.getEY());
        }
    }
}
