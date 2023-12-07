package com.group23.app.Model;
import java.awt.Point;
import java.awt.Rectangle;

public class Player extends Entity implements Collidable, Moveable {
    private double dx, dy;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Player(int width, int height) {
        this(DEFAULT_X, DEFAULT_Y, width, height);
        
    }

    @Override
    public void move() {
        x += dx;
        y += dy;
    }

    public void setSpeed(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean isOutOfBounds(int boundX, int boundY) {
        if (x>= 0 && x + width <= boundX) {
            if (y >= 0 && y + height + 10 <= boundY) {
                return false;
            }
        }
        return true;
    }

    public void reLocate(int boundX, int boundY) {

        if (x < 0) {
            x = 0;
        }
        else if (x + width > boundX) {
            x = boundX - width;
        }

        if (y < 0) {
            y = 0;
        }
        else if (y + height > boundY) {
            y = boundY - height;
        }
    }


    // --------------------- Getters -----------------------

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.getWidth(), this.getHeight());
    }

    public void modifyDx(double dx) {
        this.dx += dx;
    }

    public void modifyDy(double dy) {
        this.dy += dy;
    }

    public Point getSpeed() {
        return new Point((int)this.dx, (int)this.dy);
    }
}
