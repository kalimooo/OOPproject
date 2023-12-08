package com.group23.app;

import com.group23.app.View.GameWindow;

import com.group23.app.Controller.StateController;
import com.group23.app.Controller.PlayerController;

import com.group23.app.Model.Model;

/**
 * App containing the main game-loop
 */
public class App
{
    public static void main( String[] args )
    {
        Model model = Model.getModel();

        GameWindow window = GameWindow.getGameWindow();
        window.addStateController(new StateController(model));
        window.addPlayerController(new PlayerController(model));

        long timeSinceLastUpdate = System.currentTimeMillis();
        while (true) {
            long time = System.currentTimeMillis();
            if (time - timeSinceLastUpdate >= 2) {
                model.updateModel();
                timeSinceLastUpdate = time;
            }
        }
    }
}
