package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

/*
 * Facade class representing the model in its entirety
 */
public class Model {
    private List<Moveable> moveableObjects;
    private List<Drawable> drawableObjects;
    private static List<Laser> lasers = new ArrayList<Laser>();
    private int nmrOfLasers = 1;
    private int boundX = 800;
    private int boundY = 500;

    private static Player player;

    public Model() {
        moveableObjects = new ArrayList<>(); // TODO maybe some factory magic? :)
        drawableObjects = new ArrayList<>();
        lasers = EntityFactory.getLasers(nmrOfLasers);
        player = new Player(boundY, boundX);
    }

    public void updatePlayerSpeed(double dx, double dy) {
        player.setSpeed(dx, dy);
    }

    public void updateModel() {
        moveObjects();
        handleCollisions();
    }

    private void moveObjects() {
        for (Moveable object : moveableObjects) {
            object.move();
        }
    }

    private void handleCollisions() {
        for (Laser object : lasers) {
            if (object.collides(player)) {
                // Game over
            }
            else if (((Laser)object).isOutOfBounds(boundX, boundY)) {
                lasers.remove(object);
            }
        }
    }

    public List<Drawable> getDrawableObjects() {
        return this.drawableObjects;
    }

    static public List<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.addAll(lasers);
        entities.add(player);
        return entities;
    }
}
