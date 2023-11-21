package com.group23.app;

import com.group23.app.View.GameWindow;

import java.util.List;
import java.util.ArrayList;

import com.group23.app.Model.Drawable;
import com.group23.app.Model.Model;
import com.group23.app.Model.Collidable;
import com.group23.app.Model.Moveable;

/**
 * App containing the main game-loop
 */
public class App 
{
    public static void main( String[] args )
    {
        GameWindow gameWindow = new GameWindow();
        Model model = new Model();

        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            handleInput();

            updateModel(); // should do something like moveObjects(); and handleCollisions();
            drawObjects();
        }
    }

}
