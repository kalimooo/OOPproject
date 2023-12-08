package com.group23.app.Model;

import java.awt.Point;

public class PowerUp extends Entity{

    private static final int STANDARD_WIDTH_HEIGHT = 40;

    public PowerUp(int boundX, int boundY) {
        super(0, 0, STANDARD_WIDTH_HEIGHT, STANDARD_WIDTH_HEIGHT);
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
    
}
