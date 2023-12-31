package com.group23.app.Model;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Player extends Entity implements Moveable, Visitor {
    private double dx, dy;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    // An object that listens to changes in the state of the Player object
    private List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

    private int collectibleScore;

    private Timer powerTimer;

    private boolean isIntangible = false;

    public Player(int x, int y, int width, int height, ChangeListener changeListener) {
        super(x, y, width, height);
        collectibleScore = 0;
        changeListeners.add(changeListener);
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
        this.collectibleScore += 5;
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

    public boolean isOutOfBounds() {
        if (x>= 0 && x + width <= GameSettings.GAME_WIDTH) {
            if (y >= 0 && y + height + 10 <= GameSettings.GAME_HEIGHT) {
                return false;
            }
        }
        return true;
    }

    public void relocate() {

        if (x < 0) {
            x = 0;
        }
        else if (x + width > GameSettings.GAME_WIDTH) {
            x = GameSettings.GAME_WIDTH - width;
        }

        if (y < 0) {
            y = 0;
        }
        else if (y + height > GameSettings.GAME_HEIGHT) {
            y = GameSettings.GAME_HEIGHT - height;
        }
    }

    public void setIntangible() {
        isIntangible = true;
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
                changeListener.onChanged();
            }
        }
    }

    public void resolvePowerUpCollision(PowerUp powerUp) {
        powerDown();

        powerTimer = new Timer(powerUp.getDuration(), new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                powerDown();
                powerTimer.stop();
            }
        });
        powerTimer.start();

        powerUp.resolveCollision(this);
    }

    private void powerDown() {
        isIntangible = false;
    }

    public void resolveCollectibleItemCollision(CollectibleItem collectibleItem) {
        collectibleItem.setInactive();
        this.incrementCollectibleScore();
    }

    @Override
    public void update() {
        move();
        if (isOutOfBounds()) {
            relocate();
        }
    }

    public void resolvePlayerCollision(Player player) {}
}
