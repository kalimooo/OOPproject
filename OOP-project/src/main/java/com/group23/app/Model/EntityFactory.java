package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory {    

    // Spawns a given number of regular lasers
    public static List<Laser> spawnLasers(int nmrOfLasers) {
        List<Laser> lasers = new ArrayList<Laser>(nmrOfLasers);
        for (int i = 0; i < nmrOfLasers; i++) {
            lasers.add(new Laser());
        }
        return lasers;
    }

    // Spawns a laser with a 10% chance of being a fast laser
    public static Laser spawnLaser(StateListener stateListener) {
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

    // Spawns a random power up (as of now only the shield power up exists)
    public static PowerUp spawnPowerUp() {

        // This can later be remade to spawn a random powerup instead of always spawning a shield power
        return new ShieldPower(GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
    }

    public static CollectibleItem spawnCollectibleItem() {
        return new CollectibleItem();
    }

}
