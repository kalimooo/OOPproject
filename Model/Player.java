package Model;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements Collidable, Moveable {
    // String imagePath = "path";
    BufferedImage myPicture;

    public Player(String imagePath) {
        try {
            this.myPicture = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            System.out.println(exception);
        }
        
    }
    


    public void move() {

    }
}
