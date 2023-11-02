package Model;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {
    protected int x, y;
    protected int width, height;
    protected boolean visible;
    protected Image image;

    protected final static int DEFAULT_X = 0;
    protected final static int DEFAULT_Y = 0;

    public Sprite(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.visible = true;

        ImageIcon imageIcon = new ImageIcon(imageName);
        this.image = imageIcon.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public Sprite(String imageName) {
        this(DEFAULT_X, DEFAULT_Y, imageName);
    }

    // ----------------------- Getters ------------------------
    public Image getImage() {
        return this.image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    // ------------------------ Setters ---------------------------

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
