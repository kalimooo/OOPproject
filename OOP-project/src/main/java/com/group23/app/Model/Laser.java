package com.group23.app.Model;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Laser implements Collidable, Moveable {
    private int x, y;
    private double dx, dy;
    private int centerX, centerY = SCREEN_WIDTH/2, SCREEN_HEIGHT/2;


    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    public Laser(int x, int y, double dx, double dy) {

        if (x |= 0 y =| 0){
            switch (Math.random()) {
                case 0{
                    this.x = x;
                    this.y = 0;
                    break;
                }
            
                default{
                    this.x = x;
                    this.y = 0;
                }
            }
        }
        this.x = x;
        this.y = y;

        ArrayList<Double> speed = generateSpeed(x,y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }



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
        return new Rectangle(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
    }

    private ArrayList<Double> generateSpeed(int x, int y){

        double dx = (x - centerX);
        double dy = (y - centerY);

        dx = dx +randomDirFactor();
        dy = dy +randomDirFactor();

        ArrayList<Double> reArrayList = new ArrayList<Double>();

        reArrayList.add(dx);
        reArrayList.add(dy);

        return reArrayList;
    }

    private double randomDirFactor(){
        return Math.random();
    }


    /*public boolean collides(Sprite sprite) {
        return false;
    }*/
}
