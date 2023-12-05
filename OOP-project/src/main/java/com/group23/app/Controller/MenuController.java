package com.group23.app.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.group23.app.Model.Model;
import com.group23.app.View.GameWindow;

public class MenuController implements KeyListener,Subscriber{


    GameWindow view = GameWindow.getGameWindow();

    public MenuController() {
        super();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            Model.restartTimer();
            view.moveToGame();
            view.updateView();
            Model.startGame();
        }
        else if (code == KeyEvent.VK_BACK_SPACE) {
            view.moveToTutorial();
            view.updateView();
        }
        else if (code == KeyEvent.VK_ESCAPE) {
            view.moveToMenu();
            view.updateView();
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doAction() {
        // TODO Show a game over screen
    }
}
