package com.group23.app.Model;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory {    

    static public List<Entity> getEntities(int nmrOfLasers) {
        List<Entity> entities = new ArrayList<Entity>(nmrOfLasers);
        entities.add(new Player(0, 0, 60, 60));
        for (int i = 0; i < nmrOfLasers; i++) {
            entities.add(new Laser());
        }
        return entities;
    }
    static public List<Laser> getLasers(int nmrOfLasers) {
        List<Laser> lasers = new ArrayList<Laser>(nmrOfLasers);
        for (int i = 0; i < nmrOfLasers; i++) {
            lasers.add(new Laser());
        }
        return lasers;
    }
}
