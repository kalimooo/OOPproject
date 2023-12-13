 package com.group23.app.View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.group23.app.Model.CollectibleItem;
import com.group23.app.Model.Entity;
import com.group23.app.Model.FastLaser;
import com.group23.app.Model.Laser;
import com.group23.app.Model.Player;
import com.group23.app.Model.PowerUp;

public class Sprite extends JLabel{
    
    private static String laserBasePath = "OOP-project/src/main/java/com/group23/app/View/Images/Images/Boll_laser_bild/";

    private static ImageIcon blueLaser = GameWindow.loadScaledImage(laserBasePath + "#4deeea.png", 40, 40);
    private static ImageIcon greenLaser = GameWindow.loadScaledImage(laserBasePath + "#74ee15.png", 40, 40);
    private static ImageIcon purpleIcon = GameWindow.loadScaledImage(laserBasePath + "#f000ff.png", 40, 40);
    private static ImageIcon redIcon = GameWindow.loadScaledImage(laserBasePath + "#fc1723.png", 40, 40);
    private static ImageIcon yellowIcon = GameWindow.loadScaledImage(laserBasePath + "#ffe700.png", 40, 40);

    private static ImageIcon collectibleIcon = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/Coin1.png", 40, 40);
    private static ImageIcon shieldIcon = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/shield.png", 40, 40);

    private static ImageIcon playerIcon = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9Bresize.png", 40, 40);
    private static ImageIcon shieldedPlayerIcon = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/9BwithShield.png.png", 40, 40);

    public Sprite(Entity modelObject) {

        //Logic for painting a Projectile
        // if (modelObject instanceof Laser) {
        //     Laser object = (Laser) modelObject;
        //     Color color = object.getColor();
        //     if (color.equals(Color.decode("#4deeea"))) {
        //         setIcon(blueLaser);
        //     } else if (color.equals(Color.decode("#74ee15"))) {
        //         setIcon(greenLaser);
        //     } else if (color.equals(Color.decode("#ffe700"))) {
        //         setIcon(yellowIcon);
        //     } else if (color.equals(Color.decode("#f000ff"))) {
        //         setIcon(purpleIcon);
        //     } else if (color.equals(Color.decode("#fc1723"))) {
        //         setIcon(redIcon);
        //     }
        //     setBounds((int)modelObject.getX(), (int)modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        // }

        if(modelObject instanceof FastLaser)  {
            setIcon(blueLaser);
        }
        else if (modelObject instanceof Laser) {
            setIcon(redIcon);
        }
        
        // Logic for painting the Player
        else if (modelObject instanceof Player) {
            if (((Player)modelObject).isIntangible()) {
                setIcon(shieldedPlayerIcon);
            }
            else {
                setIcon(playerIcon);
            }
        }

        // Logic for painting a shield PowerUp
        else if (modelObject instanceof PowerUp) {
            setIcon(shieldIcon);
        }

        else if (modelObject instanceof CollectibleItem) {
            setIcon(collectibleIcon);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setBounds((int)modelObject.getX(), (int)modelObject.getY(), modelObject.getWidth(), modelObject.getHeight());
        setBackground(Color.white);
    }

    // public Sprite(Laser laser) {
    //     Color color = laser.getColor();
    //     if (color.equals(Color.decode("#4deeea"))) {
    //         setIcon(blueLaser);
    //     } else if (color.equals(Color.decode("#74ee15"))) {
    //         setIcon(greenLaser);
    //     } else if (color.equals(Color.decode("#ffe700"))) {
    //         setIcon(yellowIcon);
    //     } else if (color.equals(Color.decode("#f000ff"))) {
    //         setIcon(purpleIcon);
    //     } else if (color.equals(Color.decode("#fc1723"))) {
    //         setIcon(redIcon);
    //     }
    //     setBounds((int)laser.getX(), (int)laser.getY(), laser.getWidth(), laser.getHeight());
    //     setHorizontalAlignment(SwingConstants.CENTER);
    //     setVerticalAlignment(SwingConstants.CENTER);

    //     setBackground(Color.white);
    // }

    // public Sprite(Player player) {
    //     if (player.isIntangible()) {
    //         setIcon(shieldedPlayerIcon);
    //     } else {
    //         setIcon(playerIcon);
    //     }
    //     setBounds((int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight());
    //     setHorizontalAlignment(SwingConstants.CENTER);
    //     setVerticalAlignment(SwingConstants.CENTER);

    //     setBackground(Color.white);
    // }

    // public Sprite(PowerUp powerUp) {
    //     setIcon(shieldIcon);
    //     setBounds((int)powerUp.getX(), (int)powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
    //     setHorizontalAlignment(SwingConstants.CENTER);
    //     setVerticalAlignment(SwingConstants.CENTER);

    //     setBackground(Color.white);
    // }

    public void remake(Sprite sprite) {
        setLocation(sprite.getLocation());
        setIcon(sprite.getIcon());
    }
}
