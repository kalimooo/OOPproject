package src.test.Player;

import src.Model.Player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest {
    private Player player;

    @BeforeEach
    private void init() {
        player = new Player(10, 10, "test");
    }
}
