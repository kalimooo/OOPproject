package com.group23.app.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
/*
 * Facade class representing the model in its entirety
 */
public class Model implements StateListener{

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;

    private List<Entity> entities = new ArrayList<Entity>();
    private int boundX = SCREEN_WIDTH;
    private int boundY = SCREEN_HEIGHT;
    private static boolean gameActive = false;
    private GameClock gameClock = new GameClock();
    private Timer timer;
    private static Player player;
    private static final int TIME_FOR_MORE_LASERS = 10000; //The time is "amount of milliseconds (currently 10 seconds)" 

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
        timer = new Timer(TIME_FOR_MORE_LASERS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                entities.add(EntityFactory.spawnLaser(model));
            }
        });

        Model.model = this;
    }

    public static Model getModel() {
        if (model == null) {
            return new Model();
        }
        return model;
    }

    public long getElapsedTimeInSeconds() {
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


    // TODO Add CollisionHandler class that handles collisions
    private void handleCollisions() {
        for (Entity entity : entities) {
            if (player.collides(entity)) {
                entity.accept(player);
            }
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    private void gameOver() {
        gameActive = false;
    }

    public void onDeleted(Entity entity) {
        if (entity instanceof Laser) {
            entities.add(EntityFactory.spawnLaser(this));
        } else {
            gameOver();
        }
    }

    public void startGame() {
        gameActive = true;
        timer.start();
        System.out.println(gameActive);
    }
}
