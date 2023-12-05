package com.group23.app.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.group23.app.Controller.PlayerController;

public class PlayerControllerAdapter implements KeyListener{
    PlayerController playerController;

    public PlayerControllerAdapter(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_RIGHT:
                playerController.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                playerController.moveLeft();
                break;
            case KeyEvent.VK_UP:
                playerController.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                playerController.moveDown();
                break;
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_RIGHT:
                playerController.stopRight();
                break;
            case KeyEvent.VK_LEFT:
                playerController.stopLeft();
                break;
            case KeyEvent.VK_UP:
                playerController.stopUp();
                break;
            case KeyEvent.VK_DOWN:
                playerController.stopDown();
                break;
        }
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
