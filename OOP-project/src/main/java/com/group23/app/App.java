package com.group23.app;

import com.group23.app.View.GameWindow;

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

    private App() {
        super();
        model = new Model();
        gameWindow = GameWindow.getGameWindow();
        gameWindow.addKeyListener(new MenuController());
        gameWindow.addKeyListener(new PlayerController());
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
            if (time - timeSinceLastUpdate >= 2) {
                model.updateModel();
                gameWindow.updateView();
                timeSinceLastUpdate = time;
            }
            // handleInput();

            // updateModel(); // should do something like moveObjects(); and handleCollisions();
            // drawObjects();
        }
    }

}
