package com.group23.app.Controller;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.group23.app.View.GameWindow;

public class MenuController extends Component implements KeyListener{


    GameWindow view = GameWindow.getGameWindow();

    public MenuController() {
        super();
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            view.moveToGame();
        }
        else if (code == KeyEvent.VK_BACK_SPACE) {
            view.moveToTutorial();
        }
        else if (code == KeyEvent.VK_ESCAPE) {
            view.moveToMenu();
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
}
