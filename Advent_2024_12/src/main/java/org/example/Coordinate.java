package org.example;

import java.util.Objects;

public class Coordinate {
    private int firstCoordinate;
    private int secondCoordinate;

    public Coordinate (int firstCoordinate, int secondCoordinate) {
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
    }

    @Override
    public String toString() {
        return "(" + this.firstCoordinate + ";" + this.secondCoordinate + ")";
    }

    public int getFirstCoordinate() {
        return this.firstCoordinate;
    }

    public int getSecondCoordinate() {
        return this.secondCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate that)) return false;
        return firstCoordinate == that.firstCoordinate && secondCoordinate == that.secondCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCoordinate, secondCoordinate);
    }
}
