package com.group23.app.Model;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

public class FastLaser extends Laser {
    private double dx, dy;

    private int startBound;

    final static int SCREEN_WIDTH = Model.GAME_WIDTH;
    final static int SCREEN_HEIGHT = Model.GAME_HEIGHT;

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
    
                int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return randomNumber;
    }

    private ArrayList<Double> normalizeSpeed(double dx, double dy) {
        double magnitude = Math.sqrt(dx * dx + dy * dy);

        if (magnitude != 0) {
            dx /= magnitude;
            dy /= magnitude;
            
            //set the speed to double
            dx *= 2;
            dy *= 2;
        }

        ArrayList<Double> reArrayList = new ArrayList<Double>();
        reArrayList.add(dx);
        reArrayList.add(dy);
        return reArrayList;
    }

}
