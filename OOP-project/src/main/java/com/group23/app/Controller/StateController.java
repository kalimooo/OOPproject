package com.group23.app.Controller;

import com.group23.app.Model.Model;
import com.group23.app.View.GameWindow;

public class StateController{

    Model model;
    GameWindow view = GameWindow.getGameWindow();

    public StateController(Model model) {
        this.model = model;
    }

    public void startGame() {
        view.moveToGame();
        view.updateView();
        model.startGame();
    }

    public void restartGame() {
        model.resetGame();
    }

    public void showTutorial() {
        view.moveToTutorial();
    }

    public void showMainScreen() {
        view.moveToMenu();
    }

    public void showSettingsPage() {
        view.moveToSettingsPage();
    }

    public void resetGame() {
        model.resetGame();
    }

}
