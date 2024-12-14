package org.example;

public class Robot {
    //initial coordinates
    private int xInitial;
    private int yInitial;
    //velocity
    private int vx;
    private int vy;
    //current position..
    private int xCurr;
    private int yCurr;
    //.. at the moment t =
    private int time;
    // board size
    private int sizeX;
    private int sizeY;

    public Robot(int xInitial, int yInitial, int vx, int vy) {
        this.xInitial = xInitial;
        this.yInitial = yInitial;
        this.vx = vx;
        this.vy = vy;
        this.xCurr = xInitial;
        this.yCurr = yInitial;
        this.time = 0;
        this.sizeX = 0;
        this.sizeY = 0;
    }

    public void setBoardDimensions (int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void evolve (int time) {
        if (this.sizeX == 0 || this.sizeY == 0) {
            System.out.println("You haven't set board dimensions");
            return;
        }
        this.time += time;
        this.xCurr = (this.xCurr + this.vx * time) % this.sizeX;
        this.yCurr = (this.yCurr + this.vy * time) % this.sizeY;
        if (this.xCurr < 0) {
            this.xCurr += this.sizeX;

        }
        if (this.yCurr < 0) {
            this.yCurr += this.sizeY;

        }
    }

    public int getTime() {
        return this.time;
    }

    public int getXCurr() {
        return this.xCurr;
    }

    public int getYCurr() {
        return this.yCurr;
    }



    @Override
    public String toString() {
        return "Initial: " + this.xInitial + ";" + this.yInitial + " V: " + this.vx + ";" + this.vy
                + " Current: " + this.xCurr + "; " + this.yCurr;
    }
}
