package com.group23.app;
import com.group23.app.Model.Laser;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaserTest {
    
    @Test
    public void testLaserOutOfBounds() {
        Laser laser = new Laser();
         assertEquals(false, laser.isOutOfBounds());
    }

    @Test
    public void testLaserMove() {
        Laser laser = new Laser();
        double tempY = laser.getY();
        double tempX = laser.getX();
        laser.move();
        laser.move();
        boolean movedX = tempX != laser.getX();
        boolean movedY = tempY != laser.getY();
        assertTrue(movedX || movedY);
    }

    @Test
    public void testLaserCollides() {
        Laser laser = new Laser(0,0);
        Laser laser2 = new Laser(0,0);
        assertEquals(true, laser.collides(laser2));
    }

    @Test
    public void testLaserCollidesHitboxSize() {
        Laser laser = new Laser(0,0);
        Laser laser2 = new Laser(59,0);
        assertEquals(true, laser.collides(laser2));
    }

    @Test
    public void testLaserNotCollides() {
        Laser laser = new Laser(0,0);
        Laser laser2 = new Laser(61,0);
        assertEquals(false, laser.collides(laser2));
    }

    @Test
    public void testLaserMovingOutOfBounds() {
        Laser laser = new Laser(0,0);
        for (int i = 0; i < 1000; i++) {
            laser.move();
        }
        assertEquals(true, laser.isOutOfBounds());
    }
}
