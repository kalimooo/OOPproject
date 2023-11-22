package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

/*
 * Facade class representing the model in its entirety
 */
public class Model {
    private List<Moveable> moveableObjects;
    private List<Drawable> drawableObjects;
    private List<Collidable> collidableObjects;
    private static List<Entity> entities = new ArrayList<Entity>();

    private Player player;

    public Model() {
        moveableObjects = new ArrayList<>(); // TODO maybe some factory magic? :)
        drawableObjects = new ArrayList<>();
        collidableObjects = new ArrayList<>();

        player = new Player(null);
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
        for (Collidable object : collidableObjects) {
            if (object.collides(player)) {
                object.handleCollision();
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
