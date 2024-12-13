package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Plant {
    private char type;
    private ArrayList<Direction> boundaries;
    private Coordinate coordinate;
    private boolean registered;
    private ArrayList<Direction> boundariesToReadOff;


    public Plant (char type, int firstCoordinate, int secondCoordinate) {
        this.type = type;//aka not defined
        this.boundaries = new ArrayList<>();
        this.registered = false;
        this.coordinate = new Coordinate(firstCoordinate, secondCoordinate);
    }

    public Plant (int firstCoordinate, int secondCoordinate) {
        this.coordinate = new Coordinate(firstCoordinate, secondCoordinate);
    }

    public char getType() {
        return this.type;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public ArrayList<Direction> getBoundaries() {
        return this.boundaries;
    }

    public void setBoundaries(ArrayList<Direction> boundaries) {
        this.boundaries = boundaries;
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public void markAsRegistered() {
        this.registered = true;
        this.boundariesToReadOff = new ArrayList<Direction> (this.boundaries);
    }

    public ArrayList<Direction> getBoundariesToReadOff() {
        return this.boundariesToReadOff;
    }

    public void readOffBoundary (Direction dir) {
        this.boundariesToReadOff.remove(dir);
    }

    @Override
    public String toString() {
        return this.coordinate + " " + this.boundaries;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Plant plant)) return false;
        return Objects.equals(coordinate, plant.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinate);
    }
}
