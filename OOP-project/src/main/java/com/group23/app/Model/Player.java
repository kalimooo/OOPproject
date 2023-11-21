package com.group23.app.Model;
import java.awt.Rectangle;

public class Player extends Entity implements Collidable, Moveable {
    //private int x, y;
    private double dx, dy;

    //private Sprite sprite;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        //this.sprite = new Sprite(imageName);
    }

    // public Player(String imageName) {
    //     this(DEFAULT_X, DEFAULT_Y, imageName);
        
    // }

    @Override
    public void move() {
        x += dx;
        y += dy;
    }

    public void setSpeed(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    // --------------------- Getters -----------------------

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.getWidth(), this.getHeight());
    }
}
