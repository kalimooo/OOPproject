package src.Model;
import java.awt.Rectangle;

/*
 * Interface for anything that can collide
 */

public interface Collidable {
    // Get the hitbox of a collidable object
    public Rectangle getBounds();

    // public boolean collides(Sprite sprite);
}
