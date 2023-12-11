package com.group23.app.View;

import com.group23.app.Controller.StateController;
import com.group23.app.Controller.Subscriber;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StateControllerAdapter implements KeyListener, Subscriber{
    private StateController stateController;

    public StateControllerAdapter(StateController stateController) {
        this.stateController = stateController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            stateController.startGame();
        }
        else if (code == KeyEvent.VK_T) {
            stateController.showTutorial();
        }
        else if (code == KeyEvent.VK_ESCAPE) {
            stateController.showMainScreen();
        }
        else if (code == KeyEvent.VK_Q) {
            // TODO: Show Quit dialog
            System.out.println("User pressed Q to show quit dialog!");
        }
        else if (code == KeyEvent.VK_M) {
            // TODO: Show Quit dialog
            System.out.println("User pressed key M to mute/unmute!");
            if(GameWindow.getGameWindow().backgroundMusic.isRunning()) {
                GameWindow.getGameWindow().stopBackgroundMusic();
                PlayingMenu.getPlayingMenu().muteButton.setText("[M] Unmute");
                }
                else {GameWindow.getGameWindow().playBackgroundMusic(); 
                    PlayingMenu.getPlayingMenu().muteButton.setText("[M] Mute");}
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
