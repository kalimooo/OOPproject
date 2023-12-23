package com.group23.app.Model;
import java.awt.Point;

public class FastLaser extends Laser {
    private int startBound;
    private final double LASER_SPEED_MULTIPLIER = 1.6;

    public FastLaser() {
        super();
        startBound = (int) (Math.random() * 4);
        Point point = generateXYPoint(startBound);
        this.x = point.x;
        this.y = point.y;
    
        this.speedVector = generateSpeed();
    }

    public FastLaser(int x, int y) {
        super(x, y);
    }

    @Override
    protected DoubleVector generateSpeed() {
        DoubleVector returnVector = super.generateSpeed();
        returnVector = returnVector.scalarMultiply(LASER_SPEED_MULTIPLIER);

        return returnVector;
    }


}
