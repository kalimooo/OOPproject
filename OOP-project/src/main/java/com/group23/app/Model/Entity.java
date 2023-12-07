package com.group23.app.Model;

import java.awt.Rectangle;

public abstract class Entity {
    protected double x,y;
    protected int width,height;

    Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // If any corner of one Entity is within any corner of another Entity, they are colliding
    public boolean collides(Entity target) {
        if (this.x < target.getX() + target.getWidth() && 
            this.x + this.width > target.getX() &&
            this.y < target.getY() + target.getHeight() &&
            this.y + this.height > target.getY()) {

            return true;
        }
        return false;
    }

    public boolean collides(Rectangle targetBounds) {
        if (this.x < targetBounds.getX() + targetBounds.getWidth() && 
            this.x + this.width > targetBounds.getX() &&
            this.y < targetBounds.getY() + targetBounds.getHeight() &&
            this.y + this.height > targetBounds.getY()) {
            return true;
        }
        return false;
    }

    public boolean collides(int x, int y, int width, int height) {
        return this.collides(new Rectangle(x, y, width, height));
    }
}
