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
public class Model implements StateListener{
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 700;

    private int boundX = GAME_WIDTH;
    private int boundY = GAME_HEIGHT;

    private final double COLLECTIBLE_CHANCE = 0.05;
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
        laserTimer.start();
        collectibleTimer.start();
    }

    /**
     * Resets the game by setting the game state to inactive, clearing all entities, 
     * reinitializing the player and other entities, and restarting the timers.
     */ 
    public void resetGame() {
        Model.gameActive = false;
        entities.clear();
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40,this);
        entities.add(player);
        entities.add(EntityFactory.spawnLaser(this));
        gameClock.restartTimer();
        laserTimer.restart();
        collectibleTimer.restart();
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
    }


    /**
     * Handles the deletion of an entity from the game.
     * If the deleted entity is a Laser, a new Laser is spawned.
     * If the deleted entity is not a Laser, the game is ended.
     * @param entity The entity that was deleted.
     */
    @Override
    public void onDeleted(Entity entity) {
        if (entity instanceof Laser) {
            entities.add(EntityFactory.spawnLaser(this));
        } else {
            gameOver();
        }
    }

    
}
