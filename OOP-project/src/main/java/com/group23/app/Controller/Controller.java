package com.group23.app.Controller;

import com.group23.app.Model.Moveable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
    Moveable controlledUnit;
    private final static int MOVEMENT_SPEED = 1;

    public Controller(Moveable controlledUnit) {
        this.controlledUnit = controlledUnit;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        double dx = 0;
        double dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(!KeysFired.arrowRight) {
                    dx += MOVEMENT_SPEED;
                    KeysFired.arrowRight = true;
                }
            case KeyEvent.VK_LEFT:
                if(!KeysFired.arrowLeft) {
                    dx -= MOVEMENT_SPEED;
                    KeysFired.arrowLeft = true;
                }
            case KeyEvent.VK_UP:
                if(!KeysFired.arrowUp) {
                    dy += MOVEMENT_SPEED;
                    KeysFired.arrowUp = true;
                }
            case KeyEvent.VK_DOWN:
                if(!KeysFired.arrowDown) {
                    dy -= MOVEMENT_SPEED;
                    KeysFired.arrowDown = true;
                }
        }

        controlledUnit.setSpeed(dx, dy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        double dx = 0;
        double dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx -= MOVEMENT_SPEED;
            case KeyEvent.VK_LEFT:
                dx += MOVEMENT_SPEED;
            case KeyEvent.VK_UP:
                dy -= MOVEMENT_SPEED;
            case KeyEvent.VK_DOWN:   
                dy += MOVEMENT_SPEED; 
        }

        controlledUnit.setSpeed(dx, dy);
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
