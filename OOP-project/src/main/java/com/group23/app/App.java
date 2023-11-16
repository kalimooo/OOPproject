package com.group23.app;

import com.group23.app.View.GameWindow;

import java.util.List;
import java.util.ArrayList;

import com.group23.app.Model.Drawable;
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

        List<Drawable> drawableObjects = new ArrayList<>();

        // TODO these lists should be inside model and instantiated here
        List<Collidable> collidableObjects = new ArrayList<>();
        List<Moveable> moveableObjects = new ArrayList<>();

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
