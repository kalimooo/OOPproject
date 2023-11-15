package com.group23.app.View;
import java.awt.Color;

import javax.swing.JLabel;
import com.group23.app.Model.Projectile;

public class Laser extends JLabel{

    private Projectile projectile;
    private Color color;

    public Laser(int x, int y, String filePath, Color color) {
        super();
        this.projectile = new Projectile(x, y, filePath);
        this.setIcon(GameWindow.loadScaledImage(filePath, 15, 15));
        this.setBounds(projectile.getX(), projectile.getY(), getIcon().getIconWidth(), getIcon().getIconHeight());
        System.out.println(getIcon().getIconWidth());
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public int getEX() {
        return projectile.getX();
    }
    public int getEY() {
        return projectile.getY();
    }
}
