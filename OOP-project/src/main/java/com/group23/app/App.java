package com.group23.app;

import com.group23.app.View.ControllerAdapter;
import com.group23.app.View.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.group23.app.Controller.MenuController;
import com.group23.app.Controller.PlayerController;
import com.group23.app.Model.Model;


/**
 * App containing the main game-loop
 */
public class App
{
    private Model model;
    private GameWindow gameWindow;
    private Timer timer;

    private App() {
        super();
        model = Model.getModel();
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gameWindow.updateView();
            }
        });
        gameWindow = GameWindow.getGameWindow();
        gameWindow.addKeyListener(new MenuController());
        gameWindow.addKeyListener(new ControllerAdapter(new PlayerController()));
    }
    public static void main( String[] args )
    {
        App myApp = new App();
        myApp.gameLoop();
    }

    public void gameLoop() {
        long timeSinceLastUpdate = System.currentTimeMillis();
        while (true) {
            long time = System.currentTimeMillis();
            timer.start();
            if (time - timeSinceLastUpdate >= 2) {
                model.updateModel();
                //gameWindow.updateView();
                timeSinceLastUpdate = time;
            }
        }
    }

}
