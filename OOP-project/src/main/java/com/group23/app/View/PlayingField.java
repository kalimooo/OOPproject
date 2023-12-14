package com.group23.app.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group23.app.Controller.StateController;


public class PlayingField extends JPanel{

    static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    static final int fieldHeight = GameWindow.SCREEN_HEIGHT;

    static PlayingMenu pm = PlayingMenu.getPlayingMenu();
    private SpriteFactory spriteFactory = new SpriteFactory();
    
    JLabel playerChar;
    static boolean isVisible = false;
    List<Sprite> sprites = new ArrayList<Sprite>();
    private static ImageIcon bgImage = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Background-grid.png", GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);

    static PlayingField playingField;

    private PlayingField() {
        super(null);
        
        add(pm);

        sprites = spriteFactory.getSprites();
        for (Sprite sprite : sprites) {
            add(sprite);
        }

        setBounds(0,0, fieldWidth, fieldHeight);
        setBackground(Color.black);
        PlayingField.playingField = this;
    }

    public static PlayingField getPlayingField() {
        if (PlayingField.playingField == null) {
            return new PlayingField();
        }
        return PlayingField.playingField;
    }

    public void stateUpdate() {
        Sprite current;
        for (int i = sprites.size() - 1; i >= 0 ; i--) {
            current = sprites.get(i);
            sprites.remove(current);
            remove(current);
            repaint();
        }

        ArrayList<Sprite> newSprites = spriteFactory.getSprites();
        for (int i = 0; i < newSprites.size(); i++) {
            current = newSprites.get(i);
            sprites.add(current);
            add(sprites.get(i));
            current.repaint();
        }
    }

    public void setStateController(StateController stateController) {
        pm.setStateController(stateController);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage.getImage(), 0, 0, null);
    }
}