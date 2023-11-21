package com.group23.app.Model;
import java.awt.Rectangle;

public class Projectile extends Entity implements Collidable, Moveable, Drawable {
    //private int x, y;
    private double dx, dy;

    //private Sprite sprite;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    public Projectile(int x, int y, int width, int height) {
        super(x, y, width, height);
        //this.sprite = new Sprite(imagePath);
    }

    // public Projectile(String imagePath) {
    //     this(DEFAULT_X, DEFAULT_Y, imagePath);        
    // }

    // TODO has to check with edge of sprite instead of center of body
    public boolean isOutOfBounds(int boundX, int boundY) {
        if (this.x >= 0 && this.x <= boundX) {
            if (this.y >= 0 && this.y <= boundY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move() {
        this.x += dx;
        this.y += dy;
    }

    // -------------------------- Getters ----------------------
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.getWidth(), this.getHeight());
    }

    @Override
    public void draw() {/*TODO: Implement draw method*/}


    /*public boolean collides(Sprite sprite) {
        return false;
    }*/
}
