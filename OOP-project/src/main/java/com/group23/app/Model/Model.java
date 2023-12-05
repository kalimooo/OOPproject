package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group23.app.Controller.Subscriber;

/*
 * Facade class representing the model in its entirety
 */
public class Model {
    private List<Drawable> drawableObjects;
    private static List<Laser> lasers = new ArrayList<Laser>();
    private int nmrOfLasers = 1;
    private int maxLasers = 1;
    private int boundX = 800;
    private int boundY = 500;
    private static boolean gameActive = false;
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private Timer timer;
    private static Player player;

    private static Model model;

    private Model() {
        drawableObjects = new ArrayList<>();
        lasers = EntityFactory.getLasers(nmrOfLasers);
        player = new Player(boundX/2 - 20, boundY/2 - 20, 40, 40);
        timer = new Timer();
        Model.model = this;
    }

    public static Model getModel() {
        if (model == null) {
            return new Model();
        }
        return model;
    }

    public long getElapsedTimeInSeconds() {
        return timer.getElapsedTimeInSeconds();
    }

    public void restartTimer() {
        timer.restartTimer();
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
            revalidateLasers();
        }
    }

    private void moveObjects() {
        for (Laser laser : lasers) {
            laser.move();
        }
        player.move();
    }

    private void revalidateLasers() {
        if (nmrOfLasers < maxLasers) {
            for (int i = 0; i < maxLasers - nmrOfLasers; i++) {
                spawnLaser();
            }
        }
    }

    private void handleCollisions() {
        for (int i = 0; i < lasers.size(); i++) {
            Laser object = lasers.get(i);

            if (object.collides(player)) {
                gameOver();
            }

            else if (object.isOutOfBounds(boundX, boundY)) {

                lasers.remove(object);

                // if (object.getX() + object.getWidth() > boundX) {
                //     Point objectSpeed = object.getSpeed();
                //     object.setSpeed(objectSpeed.getX()*-1, objectSpeed.getY());
                // }
                // else if (object.getX() < 0) {
                //     Point objectSpeed = object.getSpeed();
                //     object.setSpeed(objectSpeed.getX()*-1, objectSpeed.getY());
                // }
                // if (object.getY() + object.getHeight() > boundY) {
                //     Point objectSpeed = object.getSpeed();
                //     object.setSpeed(objectSpeed.getX(), objectSpeed.getY()*-1);
                // }
                // else if (object.getY() < 0) {
                //     Point objectSpeed = object.getSpeed();
                //     object.setSpeed(objectSpeed.getX(), objectSpeed.getY()*-1);
                // }
            }
        }
        
        if (player.isOutOfBounds(boundX, boundY)) {
            player.reLocate(boundX, boundY);
        }
    }

    public List<Drawable> getDrawableObjects() {
        return this.drawableObjects;
    }

    static public List<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(player);
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

    public static void startGame() {
        Model.gameActive = true;
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
        randomTime *= (1 - Math.exp(-0.34 * time));
        return randomTime;
    }
}
