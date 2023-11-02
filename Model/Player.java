package Model;

public class Player extends Sprite implements Collidable, Moveable {

    private double dx, dy;

    public Player(int x, int y, String imageName) {
        super(x, y, imageName);
    }

    public Player(String imageName) {
        super(imageName);
        
    }
    
    public void move() {
        x += dx;
        y += dy;
    }
}
