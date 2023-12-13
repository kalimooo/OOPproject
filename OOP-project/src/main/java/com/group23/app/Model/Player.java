package com.group23.app.Model;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;


/**
 * This class represents a Player in the game. 
 * It extends Entity and implements Moveable and Visitor interfaces.
 * A Player has a position (x, y), dimensions (width, height), 
 * speed (dx, dy), a score for collectibles, and a internal timer for powerups.
 */
public class Player extends Entity implements Moveable, Visitor {
    // Boundaries of the game area
    private final static int BOUNDX = Model.GAME_WIDTH;
    private final static int BOUNDY = Model.GAME_HEIGHT;

    // Default spawning positions for a Player
    private final static int DEFAULT_X = BOUNDX/2;
    private final static int DEFAULT_Y = BOUNDY/2;

    // Player speed
    private double dx, dy;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    private final int BOUNDX = Model.SCREEN_WIDTH;
    private final int BOUNDY = Model.SCREEN_HEIGHT;

    // An object that listens to changes in the state of the Player object
    private List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

    private int collectibleScore;

    private Timer powerTimer;

    private boolean isIntangible = false;

    /**
     * Constructor for the Player class.
     * @param x The x-coordinate of the player.
     * @param y The y-coordinate of the player.
     * @param width The width of the player.
     * @param height The height of the player.
     * @param stateListener The state listener for the player.
     */
    public Player(int x, int y, int width, int height, ChangeListener changeListener) {
        super(x, y, width, height);
        collectibleScore = 0;
        changeListeners.add(changeListener);
        powerTimer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                isIntangible = false;
                powerTimer.stop();
            }
        });
    }

    public Player(int width, int height) {
        this(DEFAULT_X, DEFAULT_Y, width, height,null);
    }

    public void addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
    }

    public int getCollectibleScore() {
        return this.collectibleScore;
    }

    public void incrementCollectibleScore() {
        this.collectibleScore++;
    }

    public void resetColletibleScore() {
        this.collectibleScore = 0;
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

    public void relocate(int boundX, int boundY) {

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

    public void setIntangible() {
        isIntangible = true;
        powerTimer.start();
    }


    // --------------------- Getters -----------------------

    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.getWidth(), this.getHeight());
    }

    public void modifyDx(double dx) {
        this.dx += dx;
    }

    public boolean isIntangible() {
        return isIntangible;
    }

    public void modifyDy(double dy) {
        this.dy += dy;
    }

    public Point getSpeed() {
        return new Point((int)this.dx, (int)this.dy);
    }

    @Override
    public void accept(Visitor v) {
        v.resolvePlayerCollision(this);
    }

    public void resolveLaserCollision(Laser laser) {
        if (!isIntangible) {
            setInactive();
            laser.setInactive();
            for (ChangeListener changeListener : changeListeners) {
                changeListener.onChanged(this);
            }
        }
    }
    public void resolvePowerUpCollision(PowerUp powerUp) {
        powerUp.resolveCollision(this);
    }
    public void resolveCollectibleItemCollision(CollectibleItem collectibleItem) {
        collectibleItem.setInactive();
        this.incrementCollectibleScore();
    }

    @Override
    public void update() {
        move();
        if (isOutOfBounds(BOUNDX, BOUNDY)) {
            relocate(BOUNDX, BOUNDY);
        }
    }

    public void resolvePlayerCollision(Player player) {}
}
