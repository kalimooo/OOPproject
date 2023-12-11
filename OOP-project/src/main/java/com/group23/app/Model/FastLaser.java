package com.group23.app.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class FastLaser extends Laser {
    private double dx, dy;

    private int startBound;

    final static int SCREEN_WIDTH = Model.SCREEN_WIDTH;
    final static int SCREEN_HEIGHT = Model.SCREEN_HEIGHT;

    public FastLaser() {
        super();
        startBound = (int) (Math.random() * 4);
        Point point = generateXYPoint();
        this.x = point.x;
        this.y = point.y;
        System.out.println(x + "," + y);
    
        ArrayList<Double> speed = generateSpeed((int)x,(int)y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }

        public FastLaser(int x, int y) {
        super(60, 60);
    
        ArrayList<Double> speed = generateSpeed(x,y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }


    public boolean isOutOfBounds(int boundX, int boundY) {
        if (this.x + this.width >= 0 && this.x <= boundX) {
            if (this.y + this.height >= 0 && this.y <= boundY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move() {
        System.out.println("dx: " + dx + " dy: " + dy + "\n");
        System.out.println("x: " + x + " y: " + y);
        this.x += dx;
        this.y += dy;
    }

    // -------------------------- Getters ----------------------
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    private ArrayList<Double> generateSpeed(int x, int y){
        double dx;
        double dy;

        switch (startBound) {
            case 0: // Topp
                dx = randomDirFactor(-1, 1);
                dy = randomDirFactor(1, 2);
                break;
            case 1: // Höger
                dx = randomDirFactor(-2, -1);
                dy = randomDirFactor(-2, 2);
                break;
            case 2: // Botten
                dx = randomDirFactor(-2, 2);
                dy = randomDirFactor(-2, -1);
                break;
            default: // Vänster
                dx = randomDirFactor(1, 2);
                dy = randomDirFactor(-2, 2);
                break;
        }

        ArrayList<Double> reArrayList = normalizeSpeed(dx, dy);
        
        reArrayList = generateSpeed(reArrayList);

        return reArrayList;
    }

    private double randomDirFactor(int lowerBound, int upperBound){
        
                Random random = new Random();
    
                int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return randomNumber;
    }

    private Point generateXYPoint(){
         // Välj slumpmässigt en sida av ramen (0 = topp, 1 = höger, 2 = botten, 3 = vänster)
        int side = (int) (Math.random() * 4);

        // Slumpmässiga koordinater på den valda sidan av ramen
        int randomX = 0, randomY = 0;

        switch (side) {
            case 0: // Topp
                randomX = (int) (Math.random() * SCREEN_WIDTH); // Tar random x-värde
                randomY = 0; // Låser y-koordinat då vi vill vara längst upp på skrämen
                break;
            case 1: // Right
                randomX = SCREEN_WIDTH - this.getWidth() - 5; // Låser x så vi tittar längst bort på skärmen
                randomY = (int) (Math.random() * SCREEN_HEIGHT);
                break;
            case 2: // Botten
                randomX = (int) (Math.random() * SCREEN_WIDTH);
                randomY = SCREEN_HEIGHT - this.getHeight() - 5; // Låser y så vi alltid kollar längst ner
                break;
            case 3: // Left
                randomX = 0;
                randomY = (int) (Math.random() * SCREEN_HEIGHT);
                break;
        }

        return new Point(randomX, randomY);
    }



    @Override
    public void setSpeed(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public Point getSpeed() {
        return new Point((int)dx, (int)dy);
    }

    private ArrayList<Double> normalizeSpeed(double dx, double dy) {
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        if (magnitude != 0) {
            dx /= magnitude;
            dy /= magnitude;
        }

        System.out.println("dx: " + dx + " dy: " + dy);
        ArrayList<Double> reArrayList = new ArrayList<Double>();
        reArrayList.add(dx);
        reArrayList.add(dy);
        return reArrayList;
    }

    private ArrayList<Double> generateSpeed(ArrayList<Double> inputArrayList) {
        double dx = inputArrayList.get(0);
        double dy = inputArrayList.get(1);

        dx *= 2;
        dy *= 2;

        ArrayList<Double> reArrayList = normalizeSpeed(dx, dy);
        return reArrayList;
    }

}
