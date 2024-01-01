package com.group23.app.Model;

import java.awt.Point;

public abstract class PowerUp extends Entity{
    public PowerUp(int boundX, int boundY) {
        super(0, 0, GameSettings.STANDARD_ENTITY_WIDTH, GameSettings.STANDARD_ENTITY_HEIGHT);
        Point spawnPoint = generateSpawn(boundX, boundY);
        this.x = spawnPoint.getX();
        this.y = spawnPoint.getY();
    }

    private Point generateSpawn(int boundX, int boundY) {
        double randomX = Math.random();
        double randomY = Math.random();

        int innerBoundXMin = boundX/5;
        int innerBoundYMin = boundY/5;
        int innerBoundXMax = boundX - boundX/5;
        int innerBoundYMax = boundY - boundY/5;

        return new Point((int)(innerBoundXMin + (randomX * innerBoundXMax)) , (int)(innerBoundYMin + (randomY * innerBoundYMax)));
    }

    
    @Override
    public void update() {}

    public abstract void resolveCollision(Player player);

    /**
     * @return duration of powerup in milliseconds
     */
    public abstract int getDuration();

    @Override
    public void accept(Visitor v) {
        v.resolvePowerUpCollision(this);
    }
    
}
