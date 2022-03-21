package model;

import java.awt.*;

public class Guard  extends Sprite{
    private final int GUARD_WIDTH = 60;
    private final int GUARD_HEIGHT = 60;
    private final int WINDOW_X = 800;
    private final int WINDOW_Y = 600;
    private double velx;
    private double vely;
    public Guard(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velx = 1;
        vely = 1;
    }

    public void moveX(){
        x += velx;
        if (x + width >= 780 || x <= 0) {
            invertVelX();
        }
    }
    public void moveY(){
        y+= vely;
        if (y + width >= 560 || y <= 0) {
            invertVelY();
        }
    }
    public void invertVelX() {
        velx = -velx;
    }
    public void invertVelY() {
        vely = -vely;

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
