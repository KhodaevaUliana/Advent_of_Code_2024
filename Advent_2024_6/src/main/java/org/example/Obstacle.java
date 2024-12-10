package org.example;

public class Obstacle implements Field {
    @Override
    public boolean isObstacle() {
        return true;
    }
    @Override
    public String toString() {
        return "#";
    }
}
