package com.group23.app.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
/*
 * Facade class representing the model in its entirety
 */
public class Model implements StateListener, ChangeListener{
    private List<Entity> entities = new ArrayList<Entity>();
    private final int BOUND_X = GameSettings.GAME_WIDTH;
    private final int BOUND_Y = GameSettings.GAME_HEIGHT;
    private static boolean gameActive = false;
    private GameClock gameClock = new GameClock();
    private long finalTime = 0;

    private ChangeListener changeListener; // To be filled with a controller that can be notified when the game is no longer active

    private Timer startDelayTimer;
    private Timer lasTimer;
    private Timer powTimer;
    private Timer colTimer;

    private static Player player;

    private final double COLLECTIBLE_CHANCE = 0.4;
    private final double POWER_CHANCE = 0.1;
    private final int TIME_FOR_COLLECTIBLES = 2000; // The time is "amount of milliseconds"
    private final int TIME_FOR_MORE_LASERS = 10000; //The time is "amount of milliseconds" 
    private final int TIME_FOR_POWERS = 1500;      //          -||-

    private static Model model;

    private Model() {
        // Adding the first laser to the model
        entities.add(EntityFactory.spawnLaser(this));

        // Creating and adding the player to the model
        player = new Player(BOUND_X/2 - 20, BOUND_Y/2 - 20, 40, 40, this);
        entities.add(player);

        // The game clock which keeps track of the elapsed time in the game
        gameClock = new GameClock();

        // Timer for creating lasers. Once every TIME_FOR_LASERS milliseconds it creates a laser. As of now this means a new laser
        // every ten seconds
        lasTimer = new Timer(TIME_FOR_MORE_LASERS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                entities.add(EntityFactory.spawnLaser(model));
            }
        });
        lasTimer.stop();

        // Timer for creating collectibles. Every 20 seconds it has a 5% chance to create a collectible
        colTimer = new Timer(TIME_FOR_COLLECTIBLES, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double random = Math.random();
                if (random < COLLECTIBLE_CHANCE) {
                    entities.add(EntityFactory.spawnCollectibleItem());
                }  
            }
        });
        colTimer.stop();

        // Timer for creating powerups. Every 15 seconds there is a 10% chance to create a power up (currently only Shield powers)
        powTimer = new Timer(TIME_FOR_POWERS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double random = Math.random();
                if (random < POWER_CHANCE) {
                    entities.add(EntityFactory.spawnPowerUp());
                }
            }
        });
        powTimer.stop();
        

        // This line is for Singleton pattern, there should only be ONE model object
        Model.model = this;
    }

    // Singleton pattern implemented for the Model class
    public static Model getModel() {
        if (model == null) {
            return new Model();
        }
        return model;
    }

    public long getElapsedTimeInSeconds() {
        if (finalTime != 0) {
            return finalTime;
        }
        return gameClock.getElapsedTimeInSeconds();
    }

    public int getScore() {
        return (int) getElapsedTimeInSeconds() + player.getCollectibleScore();
    }

    public void restartTimer() {
        gameClock.restartTimer();
    }

    public void updatePlayerSpeed(double dx, double dy) {
        player.setSpeed(dx, dy);
    }

    public void modifyPlayerDX(double dx) {
        player.modifyDx(dx);
    }

    public void modifyPlayerDY(double dy) {
        player.modifyDy(dy);
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public void updateModel() {
        if (gameActive) {
            handleCollisions();
            updateObjects();
            
        }
    }

    private void updateObjects() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }

        for (int i = entities.size() - 1; i >= 0; i--) {
            if (!entities.get(i).isActive()) {
                entities.remove(i);
            }
        }
    }

    private void handleCollisions() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity curEntity = entities.get(i);
            if (player.collides(curEntity)) {
                curEntity.accept(player);
            }
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    private void gameOver() {
        gameActive = false;
        finalTime = getElapsedTimeInSeconds();
        changeListener.onChanged();

        lasTimer.stop();
        colTimer.stop();
        powTimer.stop();
    }

    @Override
    public void onDeleted() { // onDeleted is called as a Laser is being inactivated. This means a new laser should take its place
        entities.add(EntityFactory.spawnLaser(this));
    }

    @Override
    public void onChanged() {
        gameOver();
    }

    public void startGame() {
        restartTimer();
        startDelayTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gameActive = true;
                entities.add(EntityFactory.spawnLaser(model));
                lasTimer.start();
                colTimer.start();
                powTimer.start();
            }
        });
        startDelayTimer.setRepeats(false);
        startDelayTimer.start();
    }

    public void resetGame() {
        finalTime = 0;
        entities.clear();
        player = new Player(BOUND_X/2 - 20, BOUND_Y/2 - 20, 40, 40,this);
        entities.add(player);
        lasTimer.stop();
        colTimer.stop();
        powTimer.stop();
        startGame();
    }
}
