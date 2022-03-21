package model;

import java.awt.Image;


public class Bear extends Sprite{
    private final int WINDOW_X = 800;
    private final int WINDOW_Y = 600;
    private double velx;
    private double vely;


    public Bear(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velx = 10;
        vely = 10;
    }

    public void moveLeft() {
        int actualPosition = x - 10;
        if(actualPosition >= 0 && actualPosition <= WINDOW_X){
            x -= velx;
        }
    }
    public void moveRight() {
        int actualPosition = x + 10;
        if(actualPosition >= 0 && actualPosition <= WINDOW_X - 80){
            x += velx;
        }
    }
    public void moveUp() {
        int actualPosition = y - 10 ;
        if(actualPosition >= 0 && actualPosition <= WINDOW_Y + 40 ){
            y -= vely;
        }
    }

    public void moveDown() {
        int actualPosition = y + 120;
        if(actualPosition >= 0 && actualPosition <= WINDOW_Y  ){
            y += vely;
        }
    }




    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }

    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.velx = vely;
    }
}
