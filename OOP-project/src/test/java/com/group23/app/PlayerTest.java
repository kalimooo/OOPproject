package com.group23.app;

import com.group23.app.Model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testPlayerScoreIncrement() {
        Player player = new Player(40, 40);
        player.incrementCollectibleScore();
        assertEquals(1, player.getCollectibleScore());
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
        assertTrue(player.isOutOfBounds(Model.SCREEN_WIDTH, Model.SCREEN_HEIGHT));
    }

    @Test
    public void testPlayerRelocate() {
        Player player = new Player(40, 40);
        player.setSpeed(-10, -10);
        player.move();
        player.relocate(Model.SCREEN_WIDTH, Model.SCREEN_HEIGHT);
        assertFalse(player.isOutOfBounds(Model.SCREEN_WIDTH, Model.SCREEN_HEIGHT));
    }

    @Test
    public void testPlayerIntangible() {
        Player player = new Player(40, 40);
        player.setIntangible();
        assertTrue(player.isIntangible());
    }
}
