package org.example;

import java.util.ArrayList;

public class Board {
    private ArrayList<Robot> robots;
    private ArrayList<ArrayList<Integer>> boardCode;
    private int sizeX;
    private int sizeY;

    public Board (int sizeX, int sizeY, ArrayList<Robot> robots) {
        this.robots = robots;
        for (Robot robot : this.robots) {
            robot.setBoardDimensions(sizeX, sizeY);
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.initializeEmptyBoardCode();
    }

    public int getTime() {
        return this.robots.get(0).getTime();
    }

    public void initializeEmptyBoardCode() {
        this.boardCode = new ArrayList<>();
        for (int i = 0; i < sizeY; i++) {
            ArrayList<Integer> newLine = new ArrayList<>();
            for (int j = 0; j < sizeX; j++) {
                newLine.add(0);
            }
            this.boardCode.add(newLine);
        }
    }

    public void evolve (int time) {
        this.initializeEmptyBoardCode();
        for (Robot robot : this.robots) {
            robot.evolve(time);
            //update boardCode
            if (robot.getYCurr() < 0 || robot.getXCurr() < 0) {
                System.out.println("incorrect: " + robot);
                return;
            }

            int currNum = this.boardCode.get(robot.getYCurr()).get(robot.getXCurr());
            this.boardCode.get(robot.getYCurr()).set(robot.getXCurr(), currNum + 1);
        }
    }

    public boolean findAHorizontalLine(int minLength) {
        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                if (this.boardCode.get(i).get(j) >= 1) {
                    int curr = j + 1;
                    while (curr < this.sizeX && this.boardCode.get(i).get(curr) >= 1) {
                        curr ++;
                    }
                    if (curr - j >= minLength) {
                        //System.out.println("Time: " + this.robots.get(0).getTime());
                        //System.out.println("StartLine: " + i + " " + j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getSafetyFactor() {
        //define quadrants
        int startDownHalf = this.sizeY / 2;
        if (this.sizeY % 2 == 1) {
            startDownHalf += 1;
        }
        int startRightHalf = this.sizeX / 2;
        if (this.sizeX % 2 == 1) {
            startRightHalf += 1;
        }
        //first quadrant
        int first = 0;
        for (int i = 0; i < this.sizeY / 2; i++) {
            for (int j = 0; j < this.sizeX / 2; j++) {
                first += this.boardCode.get(i).get(j);
            }
        }
        System.out.println("First: " + first);
        //second quadrant
        int second = 0;
        for (int i = 0; i < this.sizeY / 2; i++) {
            for (int j = startRightHalf; j < this.sizeX; j++) {
                second += this.boardCode.get(i).get(j);
            }
        }
        System.out.println("Second: " + second);
        //third quadrant
        int third = 0;
        for (int i = startDownHalf; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX / 2; j++) {
                third += this.boardCode.get(i).get(j);
            }
        }
        System.out.println("Third: " + third);
        //4th quadrant
        int fourth = 0;
        for (int i = startDownHalf; i < this.sizeY; i++) {
            for (int j = startRightHalf; j < this.sizeX; j++) {
                fourth += this.boardCode.get(i).get(j);
            }
        }
        System.out.println("Fourth: " + fourth);

        return first * second * third * fourth;



    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                int curr = this.boardCode.get(i).get(j);
                if (curr == 0) {
                    res.append(".");
                } else {
                    res.append(curr);
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
