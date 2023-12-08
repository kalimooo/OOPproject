package com.group23.app.View;

import java.awt.Point;
import java.util.ArrayList;

import com.group23.app.Model.Entity;
import com.group23.app.Model.Model;

public class SpriteFactory {
    Model model;

    public SpriteFactory() {
        model = Model.getModel();
    }
    
    public ArrayList<Sprite> getSprites() {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        for (Entity entity : model.getEntities()) {
            sprites.add(new Sprite(entity));
        }
        return sprites;
    }

    public ArrayList<Point> getPositions() {
        ArrayList<Point> points = new ArrayList<Point>();
        for (Entity entity : model.getEntities()) {
            points.add(new Point((int)entity.getX(), (int)entity.getY()));
        }
        return points;
    }
}
