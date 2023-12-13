package com.group23.app.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
/**
 * Facade class representing the model in its entirety. 
 * This class handles the Player, all entities and the collisions between them
 */
public class Model implements StateListener, ChangeListener{

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;

    private int boundX = SCREEN_WIDTH;
    private int boundY = SCREEN_HEIGHT;
    private long finalTime = 0;

    private Timer lasTimer;
    private Timer powTimer;
    private Timer colTimer;

    private final double COLLECTIBLE_CHANCE = 0.05;
    private final double POWER_CHANCE = 0.1;
    private final int TIME_FOR_COLLECTIBLES = 2000; // The time is "amount of milliseconds"
    private final int TIME_FOR_MORE_LASERS = 10000; //The time is "amount of milliseconds" 

    private static boolean gameActive = false;

    private GameClock gameClock;
    private Timer laserTimer;
    private Timer collectibleTimer;

    private List<Entity> entities;

    private static Player player;
    private static Model model;

    private Model() {
        // Adding the first laser to the model
        entities = new ArrayList<Entity>();
        entities.add(EntityFactory.spawnLaser(this));

        // Creating and adding the player to the model, to the middle of the screen
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40, this);
        entities.add(player);

        // The game clock which keeps track of the elapsed time in the game
        gameClock = new GameClock();

        // Timer for creating lasers. Once every TIME_FOR_LASERS milliseconds it creates a laser. 
        // As of now this means a new laser every ten seconds
        laserTimer = new Timer(TIME_FOR_MORE_LASERS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                entities.add(EntityFactory.spawnLaser(model));
            }
        });

        // Timer for creating collectibles. Every 20 seconds it has a 5% chance to create a collectible
        collectibleTimer = new Timer(TIME_FOR_COLLECTIBLES, new ActionListener() {
            @Override
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

    /**
     * Method that updates entire model.
     * Should be called once per tick of the main game loop.
     * Will only update model if flag gameActive is set true
     */
    public void updateModel() {
        if (gameActive) {
            updateObjects();
            handleCollisions();
        }
    }
    /**
     * Get the elapsed time in seconds since the game started.
     * @return The elapsed time in seconds.
     */
    public long getElapsedTimeInSeconds() {
        if (finalTime != 0) {
            return finalTime;
        }
        return gameClock.getElapsedTimeInSeconds();
    }

    /**
     * Get a list of all entities in the game.
     * @return A list of all entities.
     */
    public List<Entity> getEntities() {
        return entities;
    }

    


    // ----------------- Setters to help control the player ----------------

    /**
     * Update the player's speed.
     * @param dx The change in the player's x-direction speed.
     * @param dy The change in the player's y-direction speed.
     */
    public void updatePlayerSpeed(double dx, double dy) {
        player.setSpeed(dx, dy);
    }

    /**
     * Modify the player's x-direction speed.
     * @param dx The change in the player's x-direction speed.
     */
    public void modifyPlayerDX(double dx) {
        player.modifyDx(dx);
    }

    /**
     * Modify the player's y-direction speed.
     * @param dy The change in the player's y-direction speed.
     */
    public void modifyPlayerDY(double dy) {
        player.modifyDy(dy);
    }



    // ----------------- Sets the game state -----------------
    /**
    * Starts the game by setting the game state to active and starting the timers.
    */
    public void startGame() {
        gameActive = true;
        restartTimer();
        lasTimer.start();
        colTimer.start();
        powTimer.start();
    }

    /**
     * Resets the game by setting the game state to inactive, clearing all entities, 
     * reinitializing the player and other entities, and restarting the timers.
     */ 
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

    /**
     * Restarts the game clock timer.
     */
    public void restartTimer() {
        gameClock.restartTimer();
    }
    

    /**
     * Updates all entities in the game. 
     * If an entity is not active, it is removed from the game.
     */
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


    /**
     * Handles collisions between the player and other entities in the game.
     * If the player collides with an entity, the entity's accept method is called with the player as an argument.
     * as per the Visitor pattern (See Visitor for more information)
     */
    private void handleCollisions() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity curEntity = entities.get(i);
            if (player.collides(curEntity)) {
                curEntity.accept(player);
            }
        }
    }

    /**
     * Ends the game by setting the game state to inactive.
     */
    private void gameOver() {
        gameActive = false;
        finalTime = getElapsedTimeInSeconds();
    }


    @Override
    public void onDeleted() { // onDeleted is called as a Laser is being inactivated. This means a new laser should take its place
        entities.add(EntityFactory.spawnLaser(this));
    }

    @Override
    public void onChanged(Entity entity) {
        gameOver();
    }
    
}

