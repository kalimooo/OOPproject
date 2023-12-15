package com.group23.app;

import com.group23.app.Model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testPlayerScoreIncrement() {
        Player player = new Player(40, 40);
        player.incrementCollectibleScore();
        assertEquals(5, player.getCollectibleScore());
    }

    @Test
    public void testPlayerScoreReset() {
        Player player = new Player(40, 40);
        player.incrementCollectibleScore();
        player.resetColletibleScore();
        assertEquals(0, player.getCollectibleScore());
    }

    @Test
    public void testPlayerOutOfBounds() {
        Player player = new Player(40, 40);
        player.setSpeed(-10, -10);
        player.move();
        assertTrue(player.isOutOfBounds());
    }

    @Test
    public void testPlayerRelocate() {
        Player player = new Player(40, 40);
        player.setSpeed(-10, -10);
        player.move();
        player.relocate();
        assertFalse(player.isOutOfBounds());
    }

    @Test
    public void testPlayerIntangible() {
        Player player = new Player(40, 40);
        player.setIntangible();
        assertTrue(player.isIntangible());
    }
}
