package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {    

    static public List<Laser> getLasers(int nmrOfLasers) {
        List<Laser> lasers = new ArrayList<Laser>(nmrOfLasers);
        for (int i = 0; i < nmrOfLasers; i++) {
            lasers.add(new Laser());
        }
        return lasers;
    }

    static public List<Laser> spawnLasers(int nmrOfLasers) {
        List<Laser> lasers = new ArrayList<Laser>(nmrOfLasers);
        for (int i = 0; i < nmrOfLasers; i++) {
            lasers.add(new Laser());
        }
        return lasers;
    }

    static public Laser spawnLaser(StateListener stateListener) {
        Random random = new Random();
        Laser newLaser;
        if (random.nextFloat() < 0.9) {
            newLaser = new Laser();
        } else {
            newLaser = new FastLaser();
        }
        newLaser.addStateListener(stateListener);
        return newLaser;
    }

    static public PowerUp spawnPowerUp(int boundX, int boundY) {
        return new ShieldPower(boundX, boundY);
    }
}
