package com.group23.app.View;

import java.awt.Point;
import java.util.ArrayList;

import com.group23.app.Model.Entity;
import com.group23.app.Model.Model;

public class SpriteFactory {
    
    public static ArrayList<Sprite> getSprites() {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        for (Entity entity : Model.getEntities()) {
            sprites.add(new Sprite(entity));
        }
        return sprites;
    }

    public static ArrayList<Point> getPositions() {
        ArrayList<Point> points = new ArrayList<Point>();
        for (Entity entity : Model.getEntities()) {
            points.add(new Point((int)entity.getX(), (int)entity.getY()));
        }
        return points;
    }
}
