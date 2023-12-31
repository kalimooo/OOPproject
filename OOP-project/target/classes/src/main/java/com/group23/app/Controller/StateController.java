package com.group23.app.Controller;

import com.group23.app.Model.ChangeListener;
import com.group23.app.Model.Model;
import com.group23.app.View.GameWindow;

public class StateController implements ChangeListener{

    Model model;
    GameWindow view = GameWindow.getGameWindow();

    public StateController(Model model) {
        this.model = model;
    }

    public void startGame() {
        view.moveToGame();
        view.updateView();
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

    @Override
    public void onChanged() {
        view.showGameOverMessage();
    }

    public void resetGame() {
        model.resetGame();
        view.resetView();
    }

}
