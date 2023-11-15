package com.group23.app.Model;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {
    private int width, height;
    private boolean visible;
    private Image image;

    public Sprite(String imageName) {
        this.visible = true;

        ImageIcon imageIcon = new ImageIcon(imageName);
        this.image = imageIcon.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }


    // ----------------------- Getters ------------------------

    public Image getImage() {
        return this.image;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    // ------------------------ Setters ---------------------------

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int setX(){
        return 0;
    }

    public int setY(){
        return 0;
    }
    public int getX(){
        return 0;
    }
    public int getY(){
        return 0;
    }
    
}
