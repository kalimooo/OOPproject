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
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.sound.sampled.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.group23.app.Controller.PlayerController;
import com.group23.app.Controller.StateController;

public class GameWindow extends JFrame {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;
    ContentPane contentPane = ContentPane.getContentPane();
    public static final int WINDOW_UPDATE_TIMER = 10; // Time is given in milliseconds
    static boolean gameBegun = false;
    private Timer timer;
    public Clip backgroundMusic;

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

        contentPane.addTitleScreen(TitleField.getTitleField());
        contentPane.addGameScreen(PlayingField.getPlayingField());
        contentPane.addTutorialScreen(Tutorial.getTutorial());
        contentPane.addSettingsScreen(SettingsPage.getSettingsPage());

        PlayingField.getPlayingField().setVisible(false);
        Tutorial.getTutorial().setVisible(false);
        TitleField.getTitleField().setVisible(true);
        SettingsPage.getSettingsPage().setVisible(false);
        
        loadBackgroundMusic("OOP-project/src/main/java/com/group23/app/View/music/track1.wav"); 
        setBackgroundMusicVolume(0.05);
        playBackgroundMusic();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        setResizable(false);
        setContentPane(contentPane);
        setLayout(null);
        pack();
        setVisible(true);

        GameWindow.gameWindow = this;

        // Close dialog
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Listener for the windows-window
        addWindowListener(new java.awt.event.WindowAdapter() {
            // If X-button is pressed
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmExit();
            }
        });
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
        contentPane.setStateController(stateController);
        addKeyListener(stateControllerAdapter);
    }

    public void addPlayerController(PlayerController playerController) {
        playerControllerAdapter = new PlayerControllerAdapter(playerController);
        addKeyListener(playerControllerAdapter);
    }

    public void showGameOverMessage() {
        contentPane.showGameOverMessage();
    }

    public void moveToPanel(JPanel panelToShow) {
        if (!panelToShow.isVisible()) {
            PlayingField.getPlayingField().setVisible(false);
            Tutorial.getTutorial().setVisible(false);
            TitleField.getTitleField().setVisible(false);
            SettingsPage.getSettingsPage().setVisible(false);
            panelToShow.setVisible(true);
            repaint();
        }
    }

    public void moveToTutorial() {
        contentPane.showTutorial();
        repaint();
    }

    public void moveToSettingsPage() {
        contentPane.showSettings();
        repaint();
    }

    public void moveToMenu() {
        contentPane.showMenu();
        repaint();
    }

    public void moveToGame() {
        contentPane.showGame();
        timer.start();
        repaint();
    }

    public void updateView() {
        contentPane.updateState();
        PlayingMenu.getPlayingMenu().updateScore();
        // contentPane.repaint();
    }

    public void resetView() {
        contentPane.resetState();
    }

    // Quit dialog
    private void confirmExit() {
        Color menuBackgroundColor = Color.decode("#4f504e");
        Color buttonColor = Color.decode("#767675");
        UIManager.put("OptionPane.background", menuBackgroundColor);
        UIManager.put("Panel.background", menuBackgroundColor);
        UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);

        int option = JOptionPane.showConfirmDialog(this, "<html><font color='white'> Do you want to quit?<font></html>", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Music
    private void loadBackgroundMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

            backgroundMusic = AudioSystem.getClip();

            backgroundMusic.open(audioStream);

            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.start();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    public void setBackgroundMusicVolume(double volume) {
        if (backgroundMusic != null) {
            FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }
}
