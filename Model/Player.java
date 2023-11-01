package Model;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player implements Collidable, Moveable {
    // String imagePath = "path";
    private BufferedImage image;
    private double xPos, yPos;
    private double speed;

    private static final double SPEED = 5.0;

    public Player(String imagePath) {
        this.xPos = 0;
        this.yPos = 0;
        this.speed = SPEED;
        
        
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            System.out.println(exception);
        }
        
    }
    
    public void move(double deltaTime) {
        
    }
}
