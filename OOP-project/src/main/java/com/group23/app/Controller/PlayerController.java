package com.group23.app.Controller;

import com.group23.app.Model.Model;
import com.group23.app.Model.Player;

public class PlayerController {
    Player player;
    private final static int MOVEMENT_SPEED = 1;

    public PlayerController() {
        this.player = Model.getPlayer();
    }

    public void moveRight() {
        if (!KeysFired.arrowRight) {
            player.modifyDx(MOVEMENT_SPEED);
            KeysFired.arrowRight = true;
        }
    }
    public void moveLeft() {
        if (!KeysFired.arrowLeft) {
            player.modifyDx(-MOVEMENT_SPEED);
            KeysFired.arrowLeft = true;
        }
    }
    public void moveUp() {
        if (!KeysFired.arrowUp) {
            player.modifyDy(-MOVEMENT_SPEED);
            KeysFired.arrowUp = true;
        }
    }
    public void moveDown() {
        if (!KeysFired.arrowDown) {
            player.modifyDy(MOVEMENT_SPEED);
            KeysFired.arrowDown = true;
        }
    }


// Methods to stop the players movement in a direction
    public void stopRight() {
        player.modifyDx(-MOVEMENT_SPEED);
        KeysFired.arrowRight = false;
    }
    public void stopLeft() {
        player.modifyDx(MOVEMENT_SPEED);
        KeysFired.arrowLeft = false; 
    }
    public void stopUp() {
        player.modifyDy(MOVEMENT_SPEED);
        KeysFired.arrowUp = false;   
    }
    public void stopDown() {
        player.modifyDy(-MOVEMENT_SPEED);
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
