package com.group23.app.Controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameKeyListener implements KeyListener {
    private gameController controller;

    public gameKeyListener(gameController controller) {
        this.controller = controller;
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        // Key pressed and release
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKeyCode = e.getKeyCode();
        
        switch (pressedKeyCode) {
            case KeyEvent.VK_W: // Using fallthrough
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                System.out.println(e.getKeyChar() + " pressed");
                controller.setInputReceived(true);
                break;
            default:
                System.out.println("Pressed: " + pressedKeyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Key released
    }
}
