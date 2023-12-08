package com.group23.app.Model;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Player extends Entity implements Moveable {
    private double dx, dy;

    private final static int DEFAULT_X = 0;
    private final static int DEFAULT_Y = 0;

    private int collectibleScore;

    private Timer powerTimer;

    private boolean isIntangible = false;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        collectibleScore = 0;
        powerTimer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                isIntangible = false;
                powerTimer.stop();
            }
        });
    }

    public Player(int width, int height) {
        this(DEFAULT_X, DEFAULT_Y, width, height);
        
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

    public void reLocate(int boundX, int boundY) {

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

    @Override
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
}
