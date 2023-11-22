package com.group23.app.Model;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Laser implements Collidable, Moveable {
    private int x, y;
    private double dx, dy;

    final static int SCREEN_WIDTH = 800;
    final static int SCREEN_HEIGHT = 500;

    private int centerX = SCREEN_WIDTH/2;
    private int centerY = SCREEN_HEIGHT/2;


    public Laser(int x, int y) {

            // Välj slumpmässigt en sida av ramen (0 = topp, 1 = höger, 2 = botten, 3 = vänster)
            int side = (int) (Math.random() * 4);

            // Slumpmässiga koordinater på den valda sidan av ramen
            int randomX = 0, randomY = 0;
    
            switch (side) {
                case 0: // Topp
                    randomX = (int) (Math.random() * SCREEN_WIDTH); // Tar random x-värde
                    randomY = 0; // Låser y-koordinat då vi vill vara längst upp på skrämen
                    break;
                case 1: // Höger
                    randomX = SCREEN_WIDTH; // Låser x så vi tittar längst bort på skärmen
                    randomY = (int) (Math.random() * SCREEN_HEIGHT);
                    break;
                case 2: // Botten
                    randomX = (int) (Math.random() * SCREEN_WIDTH);
                    randomY = SCREEN_HEIGHT; // Låser y så vi alltid kollar längst ner
                    break;
                case 3: // Vänster
                    randomX = 0;
                    randomY = (int) (Math.random() * SCREEN_HEIGHT);
                    break;
            }

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
    public setSpeed(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
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
