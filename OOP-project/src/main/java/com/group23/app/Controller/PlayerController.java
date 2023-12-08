package com.group23.app.Controller;

import com.group23.app.Model.Model;

public class PlayerController {
    Model model;
    private final static int MOVEMENT_SPEED = 1;//FIX

    public PlayerController(Model model) {
        this.model = model;
    }

    public void moveRight() {
        if (!KeysFired.arrowRight) {
            model.modifyPlayerDX(MOVEMENT_SPEED);
            KeysFired.arrowRight = true;
        }
    }
    public void moveLeft() {
        if (!KeysFired.arrowLeft) {
            model.modifyPlayerDX(-MOVEMENT_SPEED);
            KeysFired.arrowLeft = true;
        }
    }
    public void moveUp() {
        if (!KeysFired.arrowUp) {
            model.modifyPlayerDY(-MOVEMENT_SPEED);
            KeysFired.arrowUp = true;
        }
    }
    public void moveDown() {
        if (!KeysFired.arrowDown) {
            model.modifyPlayerDY(MOVEMENT_SPEED);
            KeysFired.arrowDown = true;
        }
    }


// Methods to stop the players movement in a direction
    public void stopRight() {
        model.modifyPlayerDX(-MOVEMENT_SPEED);
        KeysFired.arrowRight = false;
    }
    public void stopLeft() {
        model.modifyPlayerDX(MOVEMENT_SPEED);
        KeysFired.arrowLeft = false; 
    }
    public void stopUp() {
        model.modifyPlayerDY(MOVEMENT_SPEED);
        KeysFired.arrowUp = false;   
    }
    public void stopDown() {
        model.modifyPlayerDY(-MOVEMENT_SPEED);
        KeysFired.arrowDown = false;
    }

    // A class whose only use is to keep track of the keys that have recently been pressed
    private static class KeysFired {
    //Booleans that contain whether a key is currently pressed
        public static boolean arrowRight = false;
        public static boolean arrowLeft = false;
        public static boolean arrowUp = false;
        public static boolean arrowDown = false;
    }
}
