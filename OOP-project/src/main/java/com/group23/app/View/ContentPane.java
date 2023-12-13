package com.group23.app.View;

import java.awt.Color;

import javax.swing.JPanel;

import com.group23.app.Controller.StateController;

public class ContentPane extends JPanel{
    public static int SCREEN_WIDTH = GameWindow.SCREEN_WIDTH;
    public static int SCREEN_HEIGHT = GameWindow.SCREEN_HEIGHT;

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

    public void setStateController(StateController stateController) {
        playingField.setStateController();
    }
}
