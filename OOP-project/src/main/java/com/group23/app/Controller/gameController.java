package com.group23.app.Controller;


class gameController {
    // remove comments and change to correct gameModel and gameView-name 
    // private gameModel model; 
    // private gameView view; 

    public gameController(gameModel model, gameView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(new gameKeyListener());
        view.setFocusable(true);
    }

    public void gameLoop() {
        while (true) {
            if (inputReceived) {
                handleInput();
                inputReceived = false; // Reset to avoid reading same input again
            }
            update();
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput() {
        System.out.println("Input received");
    }

    private void update() {

    }

    private void repaint() {
    }

    public void setInputReceived(boolean b) {
    }
}
