package com.group23.app.View;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.group23.app.Controller.KeysFired;

public class GameWindow extends JFrame{
    
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 500;
    static final int UPDATE_SPEED = 2000000;
    static long timeForLastUpdate = System.nanoTime();
    ContentPane contentPane = ContentPane.getContentPane();
    static boolean gameBegun = false;

    public GameWindow() {
        super("Game");

        contentPane.add(TitleField.getTitleField());
        contentPane.add(PlayingField.getPlayingField());
        contentPane.add(Tutorial.getTutorial());



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        setContentPane(contentPane);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        while (true) {
            if (System.nanoTime() - timeForLastUpdate > UPDATE_SPEED) {
                if (gameBegun) {
                PlayingField.getPlayingField().repaint();                    
                if(!KeysFired.arrowDown) {
                    dy -= MOVEMENT_SPEED;
                    KeysFired.arrowDown = true;
                }
        }
                }
                timeForLastUpdate = System.nanoTime();
            }
        }
    

    static public ImageIcon loadScaledImage(String path, int preferredSizeX, int preferredSizeY) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Image scaledImg = image.getScaledInstance(preferredSizeX, preferredSizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    static public ImageIcon loadImage(String path) {
        Image image = new ImageIcon(path).getImage();
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, 50, 50, null);
        return new ImageIcon(bi);
    }


    // This might be poor design but it will work for now
    public void moveToPanel(JPanel panelToShow) {
        PlayingField.getPlayingField().setVisible(false);
        Tutorial.getTutorial().setVisible(false);
        TitleField.getTitleField().setVisible(false);
        panelToShow.setVisible(true);
        repaint();
    }
}
