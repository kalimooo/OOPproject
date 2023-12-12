package com.group23.app.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class Laser extends Entity implements Moveable {
    private double dx, dy;
    //private double centerX = Model.SCREEN_WIDTH/2;
    //private double centerY = Model.SCREEN_HEIGHT/2;

    private int startBound;
    private List<StateListener> listeners = new ArrayList<StateListener>();

    private final static int SCREEN_WIDTH = Model.SCREEN_WIDTH;
    private final static int SCREEN_HEIGHT = Model.SCREEN_HEIGHT;

    public Laser() {
        super(0, 0, 40, 40);
        startBound = (int) (Math.random() * 4);
        Point point = generateXYPoint();
        this.x = point.x;
        this.y = point.y;    
        ArrayList<Double> speed = generateSpeed((int)x,(int)y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }

    public Laser(int x, int y) {
        super(x, y, 60, 60);

        ArrayList<Double> speed = generateSpeed(x,y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }

    public void addStateListener(StateListener stateListener) {
        listeners.add(stateListener);
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

        return reArrayList;
    }

    private double randomDirFactor(int lowerBound, int upperBound){
        
                Random random = new Random();
        
                // Generate a random number within the interval [lowerBound, upperBound]
                int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return randomNumber;
    }

    public Point generateXYPoint(){

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
                randomX = SCREEN_WIDTH - this.getWidth() - 5; // Låser x så vi tittar längst bort på skärmen
                randomY = (int) (Math.random() * SCREEN_HEIGHT);
                break;
            case 2: // Botten
                randomX = (int) (Math.random() * SCREEN_WIDTH);
                randomY = SCREEN_HEIGHT - this.getHeight() - 5; // Låser y så vi alltid kollar längst ner
                break;
            case 3: // Vänster
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
        ArrayList<Double> reArrayList = new ArrayList<Double>();
        reArrayList.add(dx);
        reArrayList.add(dy);
        return reArrayList;
    }

    @Override
    public void update() {
        move();
        if (isOutOfBounds(SCREEN_WIDTH, SCREEN_HEIGHT)) {
            setInactive();
            for (StateListener stateListener : listeners) {
                stateListener.onDeleted(this);
            }
        }
    }

    @Override
    public void accept(Visitor v) {
        v.resolveLaserCollision(this);
    }
}
