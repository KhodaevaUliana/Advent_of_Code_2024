package org.example;

public class Antenna {
    private int firstCoordinate;
    private int secondCoordinate;
    private char frequency;

    public Antenna (int i, int j, char c) {
        this.firstCoordinate = i;
        this.secondCoordinate = j;
        this.frequency = c;
    }

    public char getFrequency() {
        return this.frequency;
    }

    public int getFirstCoordinate() {
        return this.firstCoordinate;
    }

    public int getSecondCoordinate() {
        return this.secondCoordinate;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(this.firstCoordinate, this.secondCoordinate);
    }

    public static boolean arePair(Antenna alpha, Antenna beta) {
        return (alpha.getFrequency() == beta.getFrequency());
    }

    public static Coordinates[] getAntinodes (Antenna alpha, Antenna beta) {
        Coordinates[] res = new Coordinates[2];
        int alpha1 = alpha.getFirstCoordinate();
        int alpha2 = alpha.getSecondCoordinate();
        int beta1 = beta.getFirstCoordinate();
        int beta2 = beta.getSecondCoordinate();
        res[0] = new Coordinates(2*alpha1 - beta1, 2*alpha2 - beta2);
        res[1] = new Coordinates(2*beta1 - alpha1, 2*beta2 - alpha2);
        return res;

    }

    @Override
    public String toString() {
        return this.firstCoordinate + " " + this.secondCoordinate + " " + this.frequency;
    }
}
