package com.group23.app.Model;

import java.awt.Point;
import java.awt.Rectangle;

public class CollectibleItem extends Entity{
    
    public CollectibleItem(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public CollectibleItem() {
        super(0, 0, 40, 40);
        Point point = generateXYPoint();
        this.x = point.x;
        this.y = point.y;
    }

    private Point generateXYPoint() {
        int x = (int) (Math.random() * Model.SCREEN_WIDTH);
        int y = (int) (Math.random() * Model.SCREEN_HEIGHT);
        return new Point(x, y);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) this.x,(int) this.y, this.width, this.height);
    }

    @Override
    public void update() {}

    @Override
    public void accept(Visitor v) {
        v.resolveCollectibleItemCollision(this);
    }
}
