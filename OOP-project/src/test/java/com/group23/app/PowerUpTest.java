package com.group23.app;

import com.group23.app.Model.*;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class PowerUpTest {
    private PowerUp powerUp;
    private Player player;

    @Before
    public void setUp() {
        powerUp = EntityFactory.spawnPowerUp(Model.SCREEN_WIDTH, Model.SCREEN_HEIGHT);
        player = new Player((int) powerUp.getX(), (int) powerUp.getY(), 40, 40, null);
    }

    @Test
    public void testPowerUpSpawnWithinBounds() {
        for (int i = 0; i < 100; i++) {
            powerUp = EntityFactory.spawnPowerUp(Model.SCREEN_WIDTH, Model.SCREEN_HEIGHT);
            assertTrue(powerUp.getX() >= 0 && powerUp.getX() < Model.SCREEN_WIDTH);
            assertTrue(powerUp.getY() >= 0 && powerUp.getY() < Model.SCREEN_HEIGHT);
        }
    }

    @Test
    public void testPowerUpCollisionWithPlayer() {
        powerUp.resolveCollision(player);
        assertTrue(player.isIntangible());
    }

    @Test
    public void testPowerUpDuration() {
        assertTrue(powerUp.getDuration() > 0);
    }
}