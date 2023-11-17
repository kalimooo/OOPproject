package com.group23.app.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayingField extends JPanel{
     
    static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    static final int fieldHeight = GameWindow.SCREEN_HEIGHT;
    
    static JLabel playerChar;
    static ArrayList<Laser> lasers = new ArrayList<Laser>();
    static boolean isVisible = false;
    static JLabel bgImage;

    static PlayingField playingField;

    private PlayingField() {
        super(null);


        playerChar = new JLabel();
        playerChar.setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png",40,40));
        playerChar.setBounds(fieldWidth/2 - 30, fieldHeight/2 - 30, playerChar.getIcon().getIconWidth(), playerChar.getIcon().getIconHeight());
        add(playerChar);

        lasers.add(new Laser(60, 60, "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/Blue.png", Color.blue));
        for (Laser laser : lasers) {
            add(laser);
        }

        bgImage = new JLabel();
        bgImage.setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Background-grid.png", GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT));
        bgImage.setBounds(0,0,bgImage.getIcon().getIconWidth(), bgImage.getIcon().getIconHeight());
        add(bgImage);

        

        setBackground(new Color(0, 0, 0, 0));
        setBounds(0,0, fieldWidth, fieldHeight);
        PlayingField.playingField = this;
    }

    public static PlayingField getPlayingField() {
        if (PlayingField.playingField == null) {
            return new PlayingField();
        }
        return PlayingField.playingField;
    }

    public void move() {
        if (isVisible) {
            playerChar.setLocation((int)playerChar.getLocation().getX() + GameWindow.dx,(int) playerChar.getLocation().getY() + GameWindow.dy);
            this.revalidate();
            this.repaint();
        }
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