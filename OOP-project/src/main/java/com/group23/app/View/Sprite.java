package com.group23.app.View;

import java.awt.Color;

import javax.swing.JLabel;

import com.group23.app.Model.Entity;
import com.group23.app.Model.Player;
import com.group23.app.Model.Projectile;

public class Sprite extends JLabel{
    
    private String laserBasePath = "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/";

    public Sprite(Entity modelObject) {

        // Logic for painting a Projectile
        if (modelObject instanceof Projectile) {
            Projectile object = (Projectile) modelObject;
            if (color.equals(Color.blue)) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "Blue.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.red)) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "Red.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.YELLOW)) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "Yellow.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.GREEN)) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "Green.png", modelObject.getWidth(), modelObject.getHeight()));
            }
            setBounds(modelObject.getX(), modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }

        // Logic for painting the Player
        else if (modelObject instanceof Player) {
            setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png", modelObject.getWidth(), modelObject.getHeight()));
            setBounds(modelObject.getX(), modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }
    }
}
