package com.group23.app.Model;

public class ShieldPower extends PowerUp{
    private final int POWERUP_DURATION = 5000;
    
    public ShieldPower(int boundX, int boundY) {
        super(boundX, boundY);
    }

    public void resolveCollision(Player player) {
        player.setIntangible();
        setInactive();
    }

    public int getDuration() {
        return POWERUP_DURATION;
    }
}
