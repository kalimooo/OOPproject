package Model;
import java.awt.Rectangle;

public class Player implements Collidable, Moveable, Drawable {
    private int x, y;
    private double dx, dy;

    private Sprite sprite;

    protected final static int DEFAULT_X = 0;
    protected final static int DEFAULT_Y = 0;

    public Player(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(imageName);
    }

    public Player(String imageName) {
        this(DEFAULT_X, DEFAULT_Y, imageName);
        
    }
    
    

    @Override
    public void move() {
        x += dx;
        y += dy;
    }


    // --------------------- Getters -----------------------

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
    }
}
