 package com.group23.app.View;

import java.awt.Color;
import javax.swing.JLabel;

import com.group23.app.Model.CollectibleItem;
import com.group23.app.Model.Entity;
import com.group23.app.Model.Laser;
import com.group23.app.Model.Player;

public class Sprite extends JLabel{
    
    private String laserBasePath = "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/";

    public Sprite(Entity modelObject) {

        // Logic for painting a Projectile
        if (modelObject instanceof Laser) {
            Laser object = (Laser) modelObject;
            Color color = object.getColor();
            if (color.equals(Color.decode("#4deeea"))) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "#4deeea.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.decode("#74ee15"))) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "#74ee15.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.decode("#ffe700"))) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "#ffe700.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.decode("#f000ff"))) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "#f000ff.png", modelObject.getWidth(), modelObject.getHeight()));
            } else if (color.equals(Color.decode("#fc1723"))) {
                setIcon(GameWindow.loadScaledImage(laserBasePath + "#fc1723.png", modelObject.getWidth(), modelObject.getHeight()));
            }
            setBounds((int)modelObject.getX(), (int)modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        } else if (modelObject instanceof CollectibleItem) {
            setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Coin1.png", modelObject.getWidth(), modelObject.getHeight()));
            setBounds((int)modelObject.getX(), (int)modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }

        // Logic for painting the Player
        else if (modelObject instanceof Player) {
            setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png", modelObject.getWidth(), modelObject.getHeight()));
            setBounds((int)modelObject.getX(), (int)modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }

        setBackground(Color.white);
    }

    public void remake(Sprite sprite) {
        setLocation(sprite.getLocation());
        setIcon(sprite.getIcon());
    }
}
