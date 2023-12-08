package com.group23.app.Model;

public abstract class Entity {
    protected double x,y;
    protected int width,height;

    Entity(double x, double y, int width, int height) {
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
}
