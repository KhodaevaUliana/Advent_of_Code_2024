package org.example;

import java.util.Objects;

public class Coordinate {
    private int first;
    private int second;

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static int distance(Coordinate A, Coordinate B) {
        int deltaFirst = A.getFirst() - B.getFirst();
        int deltaSecond = A.getSecond() - B.getSecond();
        return Math.abs(deltaFirst) + Math.abs(deltaSecond);
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate that)) return false;
        return first == that.first && second == that.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return this.first + ";" + this.second;
    }
}
