package com.group23.app.Model;

import java.awt.Rectangle;


/**
 * A generalisation of the objects within the game. 
 * To be an Entity you need the following:
 * - You need to implement the method 'public void accept(Visitor v)',
 *   The method does not need to have any functionality but it has to be there.
 *  <p>
 * - When the attribute isActive is set to false the Entity is effectively considered "dead"
 *   and is soon about to be removed entirely
 *  <p>
 * - Every Entity has an update function defined as 'public void update()', this function tells the program
 *   what things logic should be run continuously throughout the game. An example of this may be executing a 
 *   move() function. The method does not need to do anything but it has to be there
 */
public abstract class Entity {
    protected double x,y;
    protected int width,height;
    private boolean isActive = true;

    Entity(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void accept(Visitor v);

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

    public boolean isActive() {
        return isActive;
    }

    protected void setInactive() {
        isActive = false;
    }

    // If any corner of one Entity is within any corner of another Entity, they are colliding
    public boolean collides(Entity target) {
        if (this.getX() < target.getX() + target.getWidth() && 
            this.getX() + this.getWidth() > target.getX() &&
            this.getY() < target.getY() + target.getHeight() &&
            this.getY() + this.getHeight() > target.getY()) {
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

    public abstract void update();
    
}
