package com.group23.app.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import com.group23.app.Controller.Subscriber;

/*
 * Facade class representing the model in its entirety
 */
public class Model {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;

    private static List<Laser> lasers = new ArrayList<Laser>();
    private static List<PowerUp> powerUps = new ArrayList<PowerUp>();
    private int nmrOfLasers = 1;
    private int boundX = SCREEN_WIDTH;
    private int boundY = SCREEN_HEIGHT;
    private static boolean gameActive = false;
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private GameClock gameClock = new GameClock();
    private static Timer timer;
    private static Player player;
    private static final int TIME_FOR_MORE_LASERS = 10000; //The time is "amount of milliseconds (currently 10 seconds)" 

    private static Model model;

    private Model() {
        lasers = EntityFactory.getLasers(nmrOfLasers);
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40);
        gameClock = new GameClock();
        Model.model = this;
        timer = new Timer(TIME_FOR_MORE_LASERS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                spawnLaser();
                spawnShield();
            }
        });
    }

    public static Model getModel() {
        if (model == null) {
            return new Model();
        }
        return model;
    }

    private void spawnShield() {
        powerUps.add(new PowerUp(SCREEN_WIDTH, SCREEN_HEIGHT));
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
            tryToSpawnLaser(getElapsedTimeInSeconds());
            moveObjects();
            handleCollisions();
            //revalidateLasers();
        }
    }

    private void moveObjects() {
        for (Laser laser : lasers) {
            laser.move();
        }
        player.move();
    }

    private void handleCollisions() {
        for (int i = 0; i < lasers.size(); i++) {
            Laser object = lasers.get(i);

            if (object.collides(player)) {
                if (!player.isIntangible()) {
                    //gameOver();    
                }
            }

            else if (object.isOutOfBounds(boundX, boundY)) {

                lasers.remove(object);
                nmrOfLasers--;
                spawnLaser();
            }
        }

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp currPowerUp = powerUps.get(i);
            if (currPowerUp.collides(player)) {
                player.setIntangible();
                powerUps.remove(currPowerUp);
            } 
        }
        
        if (player.isOutOfBounds(boundX, boundY)) {
            player.reLocate(boundX, boundY);
        }
    }

    static public List<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(player);
        entities.addAll(powerUps);
        entities.addAll(lasers);
        return entities;
    }

    private void gameOver() {
        gameActive = false;
        notifySubscribers();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.doAction();
        }
    }

    public void startGame() {
        Model.gameActive = true;
        timer.start();
    }

    private void spawnLaser() {
        //Laser newLaser = EntityFactory.getLaser();
        Laser newLaser = new Laser();
        lasers.add(newLaser);
        nmrOfLasers++;
    }

    public void tryToSpawnLaser(double time) {
        if (chanceToFire(time) > 0.5) {
            spawnLaser();
        }
    }

    private double chanceToFire(double time) {
        Random random = new Random();
        double randomTime = random.nextDouble();
        randomTime *= (1 - Math.exp(-0.00001 * time));
        return randomTime;
    }
}
