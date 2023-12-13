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

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;

    private List<Entity> entities = new ArrayList<Entity>();
    private int boundX = SCREEN_WIDTH;
    private int boundY = SCREEN_HEIGHT;
    private static boolean gameActive = false;
    private GameClock gameClock = new GameClock();
    private long finalTime = 0;


    private Timer lasTimer;
    private Timer powTimer;
    private Timer colTimer;

    private static Player player;

    private final double COLLECTIBLE_CHANCE = 0.05;
    private final double POWER_CHANCE = 0.1;
    private final int TIME_FOR_COLLECTIBLES = 2000; // The time is "amount of milliseconds"
    private final int TIME_FOR_MORE_LASERS = 10000; //The time is "amount of milliseconds" 
    private final int TIME_FOR_POWERS = 1500;      //          -||-

    private static Model model;

    private Model() {
        // Adding the first laser to the model
        entities.add(EntityFactory.spawnLaser(this));

        // Creating and adding the player to the model
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40, this);
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

        // Timer for creating collectibles. Every 20 seconds it has a 5% chance to create a collectible
        colTimer = new Timer(TIME_FOR_COLLECTIBLES, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double random = Math.random();
                if (random < COLLECTIBLE_CHANCE) {
                    entities.add(new CollectibleItem());
                }  
            }
        });

        // Timer for creating powerups. Every 15 seconds there is a 10% chance to create a power up (currently only Shield powers)
        powTimer = new Timer(TIME_FOR_POWERS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double random = Math.random();
                if (random < POWER_CHANCE) {
                    entities.add(EntityFactory.spawnPowerUp(boundX, boundY));
                }
            }
        });
        

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

    public void updateModel() {
        if (gameActive) {
            updateObjects();
            handleCollisions();
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
    }

    @Override
    public void onDeleted(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public void onChanged(Entity entity) {
        gameOver();
    }

    public void startGame() {
        gameActive = true;
        restartTimer();
        lasTimer.start();
        colTimer.start();
        powTimer.start();
    }

    public void resetGame() {
        finalTime = 0;
        entities.clear();
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40,this);
        entities.add(player);
        entities.add(EntityFactory.spawnLaser(this));
        gameClock.restartTimer();
        lasTimer.restart();
        colTimer.restart();
        powTimer.restart();
        startGame();
    }
}
