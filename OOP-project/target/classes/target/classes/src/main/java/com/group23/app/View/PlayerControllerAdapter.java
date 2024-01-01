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
            case KeyEvent.VK_D:
                playerController.moveRight();
                break;
            case KeyEvent.VK_A:
                playerController.moveLeft();
                break;
            case KeyEvent.VK_W:
                playerController.moveUp();
                break;
            case KeyEvent.VK_S:
                playerController.moveDown();
                break;
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_D:
                playerController.stopRight();
                break;
            case KeyEvent.VK_A:
                playerController.stopLeft();
                break;
            case KeyEvent.VK_W:
                playerController.stopUp();
                break;
            case KeyEvent.VK_S:
                playerController.stopDown();
                break;
        }
        
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
