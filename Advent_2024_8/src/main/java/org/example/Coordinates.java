package org.example;

import java.util.ArrayList;

public class Coordinates {
    private int firstCoordinate;
    private int secondCoordinate;

    public Coordinates(int firstCoordinate, int secondCoordinate) {
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
    }

    public int getFirstCoordinate() {
        return this.firstCoordinate;
    }

    public int getSecondCoordinate() {
        return this.secondCoordinate;
    }

    public static ArrayList<Coordinates> line(Coordinates r1, Coordinates r2, int size) {
        int deltaX = r1.getFirstCoordinate() - r2.getFirstCoordinate();
        int deltaY = r1.getSecondCoordinate() - r2.getSecondCoordinate();
        int norm = gcd(deltaX, deltaY);
        Coordinates dir = new Coordinates(deltaX/norm, deltaY/norm);
        //forth
        int pointX = r1.getFirstCoordinate();
        int pointY = r1.getSecondCoordinate();
        ArrayList<Coordinates> res = new ArrayList<>();
        while (pointX >= 0 && pointX < size && pointY >= 0 && pointY < size) {
            res.add(new Coordinates(pointX, pointY));
            pointX += dir.getFirstCoordinate();
            pointY += dir.getSecondCoordinate();
        }
        //and back
        pointX = r1.getFirstCoordinate() - dir.getFirstCoordinate();
        pointY = r1.getSecondCoordinate() - dir.getSecondCoordinate();
        while (pointX >= 0 && pointX < size && pointY >= 0 && pointY < size) {
            res.add(new Coordinates(pointX, pointY));
            pointX -= dir.getFirstCoordinate();
            pointY -= dir.getSecondCoordinate();
        }
        return res;
    }

    public static int gcd (int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
