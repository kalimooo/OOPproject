package Model;

public class Bullet extends Sprite implements Collidable, Moveable{

    double dx, dy;

    public Bullet(String imagePath) {
        super(0, 0, imagePath);
        
    }

    public boolean isOutOfBounds(int boundX, int boundY) {
        if (this.x >= 0 && this.x <= boundX) {
            if (this.y >= 0 && this.y <= boundY) {
                return false;
            }
        }
        return true;
    }

    public void move() {
        this.x += dx;
        this.y += dy;
    }

    public boolean collides(Sprite sprite) {
        return false;
    }
}
