package com.group23.app.View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.group23.app.Model.Collidable;

// The point of the class is to act as a view version of the model objects "Projectile" and "Player"
public class GameObject extends JLabel{
    
    private Entity modelObject; // Collideable is to be replaced with an abstraction of a modelobject

    public GameObject(Collidable modelObject) {
        this.modelObject = modelObject;
        if (modelObject.getClass().getName() == "Player") {
            setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/9Bresize.png", modelObject.getWidth(), modelObject.getHeight()));
            setBounds(modelObject.getX(), modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }
        else {
            setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/Blue.png", modelObject.getWidth(), modelObject.getHeight()));
            setBounds(modelObject.getX(), modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        }
    }

}
