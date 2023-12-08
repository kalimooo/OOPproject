package com.group23.app.Model;

import java.awt.Rectangle;
import java.util.List;

public class LaserHandler {
    private static List<Laser> lasers;

    private int boundX, boundY;
    
    // TODO maybe remove if not needed
    //private int nmrOfLasers = 0;
    //private int maxLasers = 10;

    public LaserHandler(int boundX, int boundY) {
        lasers = EntityFactory.getLasers(1);
        this.boundX = boundX;
        this.boundY = boundY;
    }

    public static List<Laser> getLasers() {
        return lasers;
    }

    public void updateLasers() {
        for (Laser laser : lasers) {
            laser.move();

            if (laser.isOutOfBounds(boundX, boundY)) {
                lasers.remove(laser);

                //TODO remove this when we can spawn additional lasers
                spawnLaser();
            }
        }
    }

    public boolean isHitByLaser(Rectangle hitBounds) {
        for (Laser laser : lasers) {
            if (laser.collides(hitBounds)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHitByLaser(Entity entity) {
        for (Laser laser : lasers) {
            if (laser.collides(entity)) {
                return true;
            }
        }
        return false;
    }

    private void spawnLaser() {
        Laser laser = new Laser();
        lasers.add(laser);
    }


    // TODO maybe remove if not needed
    /*private void revalidateLasers() {
        if (nmrOfLasers < maxLasers) {
            for (int i = 0; i < maxLasers - nmrOfLasers; i++) {
                spawnLaser();
            }
        }
    }*/


}
