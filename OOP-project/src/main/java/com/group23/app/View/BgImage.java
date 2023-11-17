package com.group23.app.View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BgImage extends JPanel{
    
    public static BgImage bgImage;

    private BgImage() {
        super(null);
        setBackground(Color.CYAN);
    }


    public static BgImage getBgImage() {
        if (BgImage.bgImage == null) {
            return new BgImage();
        }
        return BgImage.bgImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Background-grid.png", GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT).getImage(), 0, 0, null);
    }
}
