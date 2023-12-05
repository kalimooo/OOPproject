package com.group23.app.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class Laser extends Entity implements Moveable {
    private double dx, dy;

    public Color laserColor = generateColor();

    final static int SCREEN_WIDTH = 800;
    final static int SCREEN_HEIGHT = 500;

    private int centerX = SCREEN_WIDTH/2;
    private int centerY = SCREEN_HEIGHT/2;


    public Laser() {
        super(0, 0, 60, 60);
        Point point = generateXYPoint();
        this.x = point.x;
        this.y = point.y;
        System.out.println(x + "," + y);
    
        ArrayList<Double> speed = generateSpeed(x,y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }

        public Laser(int x, int y) {
        super(x, y, 60, 60);
        //Point point = generateXYPoint();
        //this.x = point.x;
        //this.y = point.y;
    
        ArrayList<Double> speed = generateSpeed(x,y);

        this.dx = speed.get(0);
        this.dy = speed.get(1);
    }



    // TODO has to check with edge of sprite instead of center of body
    public boolean isOutOfBounds(int boundX, int boundY) {
        if (this.x + this.width >= 0 && this.x <= boundX) {
            if (this.y + this.height >= 0 && this.y <= boundY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move() {
        this.x += dx;
        this.y += dy;
        System.out.println(x + "," + y);
    }

    // -------------------------- Getters ----------------------
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private ArrayList<Double> generateSpeed(int x, int y){

        // double dx = (x - centerX);
        // double dy = (y - centerY);


        // TODO temporary solution, generated starting speed should be modified in relation to the size of the screen
        double dx = 1;
        double dy = 1;

        dx = dx +randomDirFactor(1,2);
        dy = dy +randomDirFactor(1,2);

        ArrayList<Double> reArrayList = new ArrayList<Double>();

        reArrayList.add(dx);
        reArrayList.add(dy);

        return reArrayList;
    }

    private double randomDirFactor(int lowerBound, int upperBound){
        
                // Create a Random object
                Random random = new Random();
        
                // Generate a random number within the interval [lowerBound, upperBound]
                int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        return randomNumber;
    }

  private Color generateColor() {
      /*#4deeea    (77,238,234)
        #74ee15    (116,238,21)
        #ffe700    (255,231,0)
        #f000ff    (240,0,255) 
        #fc1723    (252,23,35)
        */

        // TODO Remove this and add in the other colors, alternatively, change Sprite class to fit these colors
        int random = (int) (randomDirFactor(0, 3));
        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        // List<String> colors = new ArrayList<String>();
        // colors.add("#4deeea");
        // colors.add("#74ee15");
        // colors.add("#ffe700");
        // colors.add("#f000ff");
        // colors.add("#fc1723");
        
        Color randomColor = colors.get(random);
        return randomColor;
    }

    public Color getColor() {
        return this.laserColor;
    }

    private static Point generateXYPoint(){
         // Välj slumpmässigt en sida av ramen (0 = topp, 1 = höger, 2 = botten, 3 = vänster)
        int side = (int) (Math.random() * 4);

        // Slumpmässiga koordinater på den valda sidan av ramen
        int randomX = 0, randomY = 0;

        switch (side) {
            case 0: // Topp
                randomX = (int) (Math.random() * SCREEN_WIDTH); // Tar random x-värde
                randomY = 0; // Låser y-koordinat då vi vill vara längst upp på skrämen
                break;
            case 1: // Höger
                randomX = SCREEN_WIDTH; // Låser x så vi tittar längst bort på skärmen
                randomY = (int) (Math.random() * SCREEN_HEIGHT);
                break;
            case 2: // Botten
                randomX = (int) (Math.random() * SCREEN_WIDTH);
                randomY = SCREEN_HEIGHT; // Låser y så vi alltid kollar längst ner
                break;
            case 3: // Vänster
                randomX = 0;
                randomY = (int) (Math.random() * SCREEN_HEIGHT);
                break;
        }

        return new Point(randomX, randomY);
    }



    @Override
    public void setSpeed(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public Point getSpeed() {
        return new Point((int)dx, (int)dy);
    }



    /*public boolean collides(Sprite sprite) {
        return false;
    }*/
}
