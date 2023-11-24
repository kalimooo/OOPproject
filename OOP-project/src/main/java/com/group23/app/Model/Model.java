package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

/*
 * Facade class representing the model in its entirety
 */
public class Model {
    private List<Moveable> moveableObjects;
    private List<Drawable> drawableObjects;
    private static List<Entity> entities = new ArrayList<Entity>();
    private int nmrOfLasers = 1;

    private Player player;

    public Model() {
        moveableObjects = new ArrayList<>(); // TODO maybe some factory magic? :)
        drawableObjects = new ArrayList<>();
        entities = EntityFactory.getEntities(nmrOfLasers);
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
        for (Entity object : entities) {
            if (object.collides(player)) {
                // TODO handle the collision
                //object.handleCollision();
            }
        }
    }

    public List<Drawable> getDrawableObjects() {
        return this.drawableObjects;
    }

    static public List<Entity> getEntities() {
        return entities;
    }
}
