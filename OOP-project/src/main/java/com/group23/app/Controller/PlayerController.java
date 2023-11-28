package com.group23.app.Controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.group23.app.Model.Model;
import com.group23.app.Model.Moveable;
import com.group23.app.Model.Player;

public class PlayerController implements KeyListener{
    Player player;
    private final static int MOVEMENT_SPEED = 1;

    public PlayerController() {
        this.player = Model.getPlayer();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_RIGHT:
                if (!KeysFired.arrowRight) {
                    player.modifyDx(MOVEMENT_SPEED);
                    KeysFired.arrowRight = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!KeysFired.arrowLeft) {
                    player.modifyDx(-MOVEMENT_SPEED);
                    KeysFired.arrowLeft = true;
                }
                break;
            case KeyEvent.VK_UP:
                if (!KeysFired.arrowUp) {
                    player.modifyDy(-MOVEMENT_SPEED);
                    KeysFired.arrowUp = true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!KeysFired.arrowDown) {
                    player.modifyDy(MOVEMENT_SPEED);
                    KeysFired.arrowDown = true;
                }
                break;
        }




        // double dx = 0;
        // double dy = 0;

        // switch (e.getKeyCode()) {
        //     case KeyEvent.VK_RIGHT:
        //         if(!KeysFired.arrowRight) {
        //             dx += MOVEMENT_SPEED;
        //             KeysFired.arrowRight = true;
        //         }
        //         break;
        //     case KeyEvent.VK_LEFT:
        //         if(!KeysFired.arrowLeft) {
        //             dx -= MOVEMENT_SPEED;
        //             KeysFired.arrowLeft = true;
        //         }
        //         break;
        //     case KeyEvent.VK_UP:
        //         if(!KeysFired.arrowUp) {
        //             dy -= MOVEMENT_SPEED;
        //             KeysFired.arrowUp = true;
        //         }
        //         break;
        //     case KeyEvent.VK_DOWN:
        //         if(!KeysFired.arrowDown) {
        //             dy += MOVEMENT_SPEED;
        //             KeysFired.arrowDown = true;
        //         }
        //         break;
        // }

        // player.setSpeed(dx, dy);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_RIGHT:
                player.modifyDx(-MOVEMENT_SPEED);
                KeysFired.arrowRight = false;
                break;
            case KeyEvent.VK_LEFT:
                player.modifyDx(MOVEMENT_SPEED);
                KeysFired.arrowLeft = false;
                break;
            case KeyEvent.VK_UP:
                player.modifyDy(MOVEMENT_SPEED);
                KeysFired.arrowUp = false;
                break;
            case KeyEvent.VK_DOWN:
                player.modifyDy(-MOVEMENT_SPEED);
                KeysFired.arrowDown = false;
                break;
        }

        // double dx = 0;
        // double dy = 0;

        // switch (e.getKeyCode()) {
        //     case KeyEvent.VK_RIGHT:
        //         dx -= MOVEMENT_SPEED;
        //         KeysFired.arrowRight = false;
        //     case KeyEvent.VK_LEFT:
        //         dx += MOVEMENT_SPEED;
        //         KeysFired.arrowLeft = false;
        //     case KeyEvent.VK_UP:
        //         dy += MOVEMENT_SPEED;
        //         KeysFired.arrowUp = false;
        //     case KeyEvent.VK_DOWN:   
        //         dy -= MOVEMENT_SPEED; 
        //         KeysFired.arrowDown = false;
        // }

        // //player.setSpeed(dx, dy);
        // Point currentSpeed = player.getSpeed();

        // player.setSpeed(currentSpeed.getX() + dx, currentSpeed.getY() + dy);
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
