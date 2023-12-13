package com.group23.app.View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

public class ContentPane extends JPanel{
    public static int SCREEN_WIDTH = GameWindow.SCREEN_WIDTH;
    public static int SCREEN_HEIGHT = GameWindow.SCREEN_HEIGHT;

    private PlayingField playingField;
    private Tutorial tutorial;
    private TitleField titleField;
    private SettingsPage settingsPage;

    public static ContentPane contentPane;

    private ContentPane() {
        super(null);
        setBackground(Color.BLACK);
        ContentPane.contentPane = this;
    }

    public static ContentPane getContentPane() {
        if (ContentPane.contentPane == null) {
            return new ContentPane();
        }
        return ContentPane.contentPane;
    }

    public void addGameScreen(PlayingField playingField) {
        this.playingField = playingField;
        this.add(playingField);
    }

    public void addTitleScreen(TitleField titleField) {
        this.titleField = titleField;
        this.add(titleField);
    }

    public void addTutorialScreen(Tutorial tutorial) {
        this.tutorial = tutorial;
        this.add(tutorial);
    }

    public void addSettingsScreen(SettingsPage settingsPage) {
        this.settingsPage = settingsPage;
        this.add(settingsPage);
    }

    public void showGameOverMessage() {
        playingField.showDeathMessage();
    }

    public void showTutorial() {
        setAllInvisible();
        tutorial.setVisible(true);
    }

    public void showSettings() {
        setAllInvisible();
        settingsPage.setVisible(true);
    }

    public void showMenu() {
        setAllInvisible();
        titleField.setVisible(true);
    }

    public void showGame() {
        setAllInvisible();
        playingField.setVisible(true);
    }

    public void setAllInvisible() {
        playingField.setVisible(false);
        tutorial.setVisible(false);
        titleField.setVisible(false);
        settingsPage.setVisible(false);
    }

    public void updateState() {
        playingField.stateUpdate();
    }
}
