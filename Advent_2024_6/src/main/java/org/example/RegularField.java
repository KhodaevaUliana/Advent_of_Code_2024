package org.example;

import java.util.ArrayList;

public class RegularField implements Field {
    private boolean isVisited;
    private ArrayList<Direction> oldDirections;

    public RegularField() {
        this.oldDirections = new ArrayList<>();
        this.isVisited = false;
    }

    public void markVisited() {
        this.isVisited = true;
    }

    public boolean getVisited() {
        return this.isVisited;
    }

    public boolean addDirection (Direction dir) {
        boolean foundCycle = false;
        if (this.oldDirections.contains(dir)) {
            foundCycle = true;
        } else {
            this.oldDirections.add(dir);
        }
        return foundCycle;
    }

    public  ArrayList<Direction> getOldDirections() {
        return this.oldDirections;
    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public String toString() {
        if (isVisited) {
            return "X";
        } else {
            return ".";
        }
    }
}
