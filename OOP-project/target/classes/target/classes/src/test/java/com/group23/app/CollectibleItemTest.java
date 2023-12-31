package com.group23.app;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import com.group23.app.Model.*;

public class CollectibleItemTest {
    private Player player;
    private CollectibleItem collectibleItem;

    @Before
    public void setUp() {
        player = new Player(0, 0, 40, 40, null);
        collectibleItem = EntityFactory.spawnCollectibleItem();
    }

    @Test
    public void testDefaultConstructor() {
        CollectibleItem item = new CollectibleItem();
        assertTrue(item.getX() >= 0 && item.getX() < GameSettings.GAME_WIDTH);
        assertTrue(item.getY() >= 0 && item.getY() < GameSettings.GAME_HEIGHT);
    }

    @Test
    public void testBounds() {
        CollectibleItem item = new CollectibleItem(10, 20, 30, 40);
        Rectangle expectedBounds = new Rectangle(10, 20, 30, 40);
        assertEquals(expectedBounds, item.getBounds());
    }
    
    @Test
    public void testPlayerCollisionWithCollectibleItemIncreasesScore() {
        // Act
        player.resolveCollectibleItemCollision(collectibleItem);

        // Assert
        assertEquals(5, player.getCollectibleScore());
    }

    @Test
    public void testCollectibleItemInactiveAfterCollision() {
        // Act
        player.resolveCollectibleItemCollision(collectibleItem);

        // Assert
        assertFalse(collectibleItem.isActive());
    }
}