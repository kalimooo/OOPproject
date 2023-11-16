package com.group23.app.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tutorial extends JPanel{
    
    static final int SCREEN_WIDTH = GameWindow.SCREEN_WIDTH;
    static final int SCREEN_HEIGHT = GameWindow.SCREEN_HEIGHT;

    JLabel text = new JLabel("<html>The goal of the game is to avoid being hit by the laserbeams.<br> You move by using:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; The laserbeam will come in from directions around the screen in a rate that is increasing with the longer you play.</html>");

    JLabel title = new JLabel("Tutorial");

    JLabel WASD_image = new JLabel();

    static Tutorial tutorial;

    private Tutorial(){
        super(null);

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

        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);

        add(WASD_image);
        add(title);
        add(text);

        Tutorial.tutorial = this;
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

public static Tutorial getTutorial() {
    if (Tutorial.tutorial == null) {
        return new Tutorial();
    }
    return Tutorial.tutorial;
}

    
}
