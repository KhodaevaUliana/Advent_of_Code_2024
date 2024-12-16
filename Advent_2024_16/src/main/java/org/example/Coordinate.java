package org.example;

public class Coordinate {
    private int first;
    private int second;

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }


    @Override
    public String toString() {
        return this.first + ";" + this.second;
    }
}
