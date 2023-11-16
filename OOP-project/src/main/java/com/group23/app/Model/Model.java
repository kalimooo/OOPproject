package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Moveable> moveableObjects;
    private List<Drawable> drawableObjects;
    private List<Collidable> collidableObjects;

    private Player player;

    public Model() {
        moveableObjects = new ArrayList<>();
        drawableObjects = new ArrayList<>();
        collidableObjects = new ArrayList<>();

        player = new Player(null);
    }

    public void updateModel() {

    }

    private void moveObjects() {
        for (Moveable object : moveableObjects) {
            object.move();
        }
    }

    private void handleCollision() {
        for (Collidable object : collidableObjects) {
            if (object.collides(player)) {
                object.handleCollision();
            }
        }
    }
}
