package Model;

public class Bullet extends Sprite implements Collidable, Moveable{

    
    private double size;
    private double dir;

    public void Bullet( double size, double dir){

        this.size = size;
        this.dir = dir;
    }

    public void move(double deltaTime) {

    }

}
