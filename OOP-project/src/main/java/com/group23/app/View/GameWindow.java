package com.group23.app.View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.group23.app.Controller.PlayerController;
import com.group23.app.Controller.StateController;

public class GameWindow extends JFrame{
    
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;
    ContentPane contentPane = ContentPane.getContentPane();
    public static final int WINDOW_UPDATE_TIMER = 10; //Time is given in milliseconds
    static boolean gameBegun = false;
    private Timer timer;

    private PlayerControllerAdapter playerControllerAdapter;
    private StateControllerAdapter stateControllerAdapter;

    static private GameWindow gameWindow;

    private GameWindow() {
        super("Game");

        timer = new Timer(WINDOW_UPDATE_TIMER, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updateView();
            }
        });

        contentPane.add(TitleField.getTitleField());
        contentPane.add(PlayingField.getPlayingField());
        contentPane.add(Tutorial.getTutorial());


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        setResizable(false);
        setContentPane(contentPane);
        setLayout(null);
        setVisible(true);

        GameWindow.gameWindow = this;
    }

    

    static public GameWindow getGameWindow() {
        if (gameWindow == null) {
            return new GameWindow();
        }
        return GameWindow.gameWindow;
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

    public void addStateController(StateController stateController) {
        stateControllerAdapter = new StateControllerAdapter(stateController);
        addKeyListener(stateControllerAdapter);
    }

    public void addPlayerController(PlayerController playerController) {
        playerControllerAdapter = new PlayerControllerAdapter(playerController);
        addKeyListener(playerControllerAdapter);
    }


    // This might be poor design but it will work for now
    public void moveToPanel(JPanel panelToShow) {
        if (!panelToShow.isVisible()) {
            PlayingField.getPlayingField().setVisible(false);
            Tutorial.getTutorial().setVisible(false);
            TitleField.getTitleField().setVisible(false);
            panelToShow.setVisible(true);
            repaint();
        }
    }

    public void moveToTutorial() {
        PlayingField.getPlayingField().setVisible(false);
        Tutorial.getTutorial().setVisible(true);
        TitleField.getTitleField().setVisible(false);
        repaint();
    }
    public void moveToMenu() {
        PlayingField.getPlayingField().setVisible(false);
        Tutorial.getTutorial().setVisible(false);
        TitleField.getTitleField().setVisible(true);
        repaint();
    }

    public void moveToGame() {
        PlayingField.getPlayingField().setVisible(true);
        Tutorial.getTutorial().setVisible(false);
        TitleField.getTitleField().setVisible(false);
        timer.start();
        repaint();
    }

    public void updateView() {
        PlayingField.getPlayingField().stateUpdate();
        PlayingMenu.getPlayingMenu().updateTime();
        //contentPane.repaint();
    }
}
