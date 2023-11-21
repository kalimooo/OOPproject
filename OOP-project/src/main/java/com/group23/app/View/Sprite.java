package com.group23.app.View;

import java.awt.Color;

import javax.swing.JLabel;

public class Sprite extends JLabel{
    
    private String laserBasePath = "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/";

    private Entity modelObject;

    public Sprite(Entity modelObject, Color color) {
        this.modelObject = modelObject;
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

    public Sprite(Entity modelObject) {
        this.modelObject = modelObject;
        setIcon(GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png", modelObject.getWidth(), modelObject.getHeight()));
        setBounds(modelObject.getX(), modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
    }

}
