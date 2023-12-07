package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group23.app.Controller.Subscriber;

/*
 * Facade class representing the model in its entirety
 */
public class Model {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;

    private List<Drawable> drawableObjects;
    private static List<Laser> lasers = new ArrayList<Laser>();
    private int nmrOfLasers = 1;
    private int maxLasers = 1;
    private int boundX = SCREEN_WIDTH;
    private int boundY = SCREEN_HEIGHT;
    private static boolean gameActive = false;
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private static Timer timer = new Timer();
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

    public static void restartTimer() {
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
          //  System.out.println(nmrOfLasers);
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
                //gameOver();
            }

            else if (object.isOutOfBounds(boundX, boundY)) {

                lasers.remove(object);
                nmrOfLasers--;
                spawnLaser();
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
        randomTime *= (1 - Math.exp(-0.00001 * time));
        return randomTime;
    }
}
