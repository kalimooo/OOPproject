package com.group23.app.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Tutorial extends JFrame implements KeyListener{
    
    static final int SCREEN_WIDTH = GameWindow.SCREEN_WIDTH;
    static final int SCREEN_HEIGHT = GameWindow.SCREEN_HEIGHT;

    ContentPane tutorialPane = ContentPane.getContentPane();

    JLabel text = new JLabel("<html>The goal of the game is to avoid being hit by the laserbeams.<br> You move by using:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; The laserbeam will come in from directions around the screen in a rate that is increasing with the longer you play.</html>");

    JLabel title = new JLabel("Tutorial");

    JLabel WASD_image = new JLabel();

    public Tutorial(){
        super("Game");

        text.setForeground(Color.WHITE);

        text.setForeground(Color.WHITE);
        text.setFont(new Font(text.getFont().getName(), Font.PLAIN, 30));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.TOP);
        text.setBounds(70,80, SCREEN_WIDTH-100, SCREEN_HEIGHT);
        
        title.setForeground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setBounds(0,0, SCREEN_WIDTH, SCREEN_HEIGHT/4);

        WASD_image.setIcon(Tutorial.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/WASD.png", 87, 50));
        WASD_image.setBounds(SCREEN_WIDTH/2 - 30, SCREEN_HEIGHT - 350, WASD_image.getIcon().getIconWidth(), WASD_image.getIcon().getIconHeight());

        tutorialPane.add(WASD_image);
        tutorialPane.add(title);
        tutorialPane.add(text);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        addKeyListener(this);
        setLocationRelativeTo(null);

        setContentPane(tutorialPane);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    

public static void main(String[] args) {
    Tutorial tutorial = new Tutorial();
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

    
}
