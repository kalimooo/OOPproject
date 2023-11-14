package src.Controller;

class gameController {
    // remove comments and change to correct gameModel and gameView-name 
    // private gameModel model; 
    // private gameView view; 

    public gameController(gameModel model, gameView view) {
        this.model = model; 
        this.view = view; 
    }

    public void handleInput() {

    }

    public void gameLoop() {
        while (true) {
            handleInput();
            update();
            repaint();

        }

    }
}