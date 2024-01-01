package com.group23.app.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;

public class Laser extends Entity implements Moveable {
    protected DoubleVector speedVector;
    private final double SPAWN_OFFSET = Math.PI / 10; // Spawning offset in radians

    private int startBound;
    private List<StateListener> listeners = new ArrayList<StateListener>();

    public Laser() {
        super(0, 0, 40, 40);
        startBound = (int) (Math.random() * 4);
        Point point = generateXYPoint(startBound);
        this.x = point.x;
        this.y = point.y;
        this.speedVector = generateSpeed();
    }

    public Laser(int x, int y) {
        super(x, y, 60, 60);

        this.speedVector = generateSpeed();

    }

    public void addStateListener(StateListener stateListener) {
        listeners.add(stateListener);
    }


    public boolean isOutOfBounds() {
        if (this.x + this.width >= 0 && this.x <= GameSettings.GAME_WIDTH) {
            if (this.y + this.height >= 0 && this.y <= GameSettings.GAME_HEIGHT) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move() {
        this.x += speedVector.getX();
        this.y += speedVector.getY();
    }

    // -------------------------- Getters ----------------------
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    protected DoubleVector generateSpeed(){
        DoubleVector returnSpeed = new DoubleVector(GameSettings.GAME_WIDTH/2 - this.x, GameSettings.GAME_HEIGHT/2 - this.y);
        
        double angleOffset = ThreadLocalRandom.current().nextDouble(-SPAWN_OFFSET, SPAWN_OFFSET);
        
        returnSpeed.rotate(angleOffset);
        returnSpeed.normalize();

        return returnSpeed;
    }

    public Point generateXYPoint(int side){

         // Välj slumpmässigt en sida av ramen (0 = topp, 1 = höger, 2 = botten, 3 = vänster)

        // Slumpmässiga koordinater på den valda sidan av ramen
        int randomX = 0, randomY = 0;

        switch (side) {
            case 0: // Topp
                randomX = (int) (Math.random() * GameSettings.GAME_WIDTH); // Tar random x-värde
                randomY = 0; // Låser y-koordinat då vi vill vara längst upp på skrämen
                break;
            case 1: // Höger
                randomX = GameSettings.GAME_WIDTH - this.getWidth() - 5; // Låser x så vi tittar längst bort på skärmen
                randomY = (int) (Math.random() * GameSettings.GAME_HEIGHT);
                break;
            case 2: // Botten
                randomX = (int) (Math.random() * GameSettings.GAME_WIDTH);
                randomY = GameSettings.GAME_HEIGHT - this.getHeight() - 5; // Låser y så vi alltid kollar längst ner
                break;
            case 3: // Vänster
                randomX = 0;
                randomY = (int) (Math.random() * GameSettings.GAME_HEIGHT);
                break;
        }

        return new Point(randomX, randomY);
    }

    @Override
    public void setSpeed(double dx, double dy) {
        this.speedVector.set(dx, dy);
    }
    
    public Point getSpeed() {
        return new Point((int)speedVector.getX(), (int)speedVector.getY());
    }


    @Override
    public void update() {
        move();
        if (isOutOfBounds()) {
            setInactive();
            for (StateListener stateListener : listeners) {
                stateListener.onDeleted();
            }
        }
    }

    @Override
    public void accept(Visitor v) {
        v.resolveLaserCollision(this);
    }
}
