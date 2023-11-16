package com.group23.app.View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements KeyListener{
    
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 500;
    ContentPane contentPane = ContentPane.getContentPane();

    GameWindow() {
        super("Game");

        contentPane.add(TitleField.getTitleField());
        contentPane.add(PlayingField.getPlayingField());
        contentPane.add(Tutorial.getTutorial());



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        addKeyListener(this);
        setLocationRelativeTo(null);

        setContentPane(contentPane);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.contentPane.remove(TitleField.titleField);
            this.contentPane.remove(Tutorial.tutorial);
            this.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            this.contentPane.remove(TitleField.titleField);
            this.contentPane.remove(PlayingField.playingField);
            this.repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    static public ImageIcon loadScaledImage(String path, int preferredSizeX, int preferredSizeY) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Image scaledImg = image.getScaledInstance(preferredSizeY, preferredSizeX, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    static public ImageIcon loadImage(String path) {
        Image image = new ImageIcon(path).getImage();
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, 50, 50, null);
        return new ImageIcon(bi);
    }
}
