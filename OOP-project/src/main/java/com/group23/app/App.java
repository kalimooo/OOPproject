package com.group23.app;

import com.group23.app.View.GameWindow;

import com.group23.app.Controller.MenuController;
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
    }
    public static void main( String[] args )
    {
        App myApp = new App();
        myApp.gameLoop();
    }

    public void gameLoop() {
        while (true) {

            model.updateModel();
            gameWindow.updateView();


            // handleInput();

            // updateModel(); // should do something like moveObjects(); and handleCollisions();
            // drawObjects();
        }
    }

}
