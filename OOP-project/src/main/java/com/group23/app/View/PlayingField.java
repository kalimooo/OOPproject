package com.group23.app.View;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group23.app.Model.Entity;
import com.group23.app.Model.Model;

public class PlayingField extends JPanel{
     
    static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    static final int fieldHeight = GameWindow.SCREEN_HEIGHT;
    
    static JLabel playerChar;
    static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    static boolean isVisible = false;
    static JLabel bgImage;

    static PlayingField playingField;

    private PlayingField() {
        super(null);

        sprites = SpriteFactory.getSprites();
        for (Sprite sprite : sprites) {
            add(sprite);
        }
        playerChar = new JLabel();
        playerChar.setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png",40,40));
        playerChar.setBounds(fieldWidth/2 - 30, fieldHeight/2 - 30, playerChar.getIcon().getIconWidth(), playerChar.getIcon().getIconHeight());
        add(playerChar);

        bgImage = new JLabel();
        bgImage.setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Background-grid.png", GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT));
        bgImage.setBounds(0,0,bgImage.getIcon().getIconWidth(), bgImage.getIcon().getIconHeight());
        add(bgImage);

        setBounds(0,0, fieldWidth, fieldHeight);
        PlayingField.playingField = this;
    }

    public static PlayingField getPlayingField() {
        if (PlayingField.playingField == null) {
            return new PlayingField();
        }
        return PlayingField.playingField;
    }

    public void update() {
        this.removeAll();
        for (Entity entity : Model.getEntities()) {
            add(new Sprite(entity));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
