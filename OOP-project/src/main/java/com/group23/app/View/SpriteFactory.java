package com.group23.app.View;

import java.util.ArrayList;

import com.group23.app.Model.Entity;
import com.group23.app.Model.Model;

public class SpriteFactory {
    
    static private ArrayList<Sprite> sprites;

    static ArrayList<Sprite> getSprites() {
        sprites = new ArrayList<Sprite>();
        for (Entity entity : Model.getEntities()) {
            sprites.add(new Sprite(entity));
        }
        return sprites;
    }
}
