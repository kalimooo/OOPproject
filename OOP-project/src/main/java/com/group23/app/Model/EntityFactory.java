package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

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

        Laser newLaser = new Laser();
        newLaser.addStateListener(stateListener);
        return newLaser;
    }
}
