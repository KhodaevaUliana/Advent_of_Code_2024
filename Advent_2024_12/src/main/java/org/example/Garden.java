package org.example;

import java.util.ArrayList;

public class Garden {
    private ArrayList<ArrayList<Plant>> plants;
    private ArrayList<Region> regions;

    public Garden(ArrayList<ArrayList<Character>> map) {
        this.plants = new ArrayList<>();
        this.regions = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            ArrayList<Plant> newLine = new ArrayList<>();
            for (int j = 0; j < map.get(i).size(); j++) {
                newLine.add(new Plant(map.get(i).get(j), i, j));
            }
            this.plants.add(newLine);
        }
    }

    public void register() {
        Coordinate first = this.findUnregistered();
        while (first.getFirstCoordinate() >= 0 && first.getSecondCoordinate() >= 0) {
            char currType = this.plants.get(first.getFirstCoordinate()).get(first.getSecondCoordinate()).getType();
            Region newRegion = new Region(currType);
            ArrayList<Coordinate> stack = new ArrayList<>();
            stack.add(first);
            while (!stack.isEmpty()) {
                Coordinate curr = stack.remove(0);
                int x = curr.getFirstCoordinate();
                int y = curr.getSecondCoordinate();
                newRegion.add(this.plants.get(x).get(y));
                ArrayList<Direction> boundaries = new ArrayList<>();
                if (x > 0 && this.plants.get(x - 1).get(y).getType() == currType) {
                    if (!this.plants.get(x-1).get(y).isRegistered() && !stack.contains(new Coordinate(x-1, y))) {
                        stack.add(new Coordinate(x-1, y));
                    }
                } else {
                    boundaries.add(Direction.UP);
                }
                if (y > 0 && this.plants.get(x).get(y-1).getType() == currType) {
                    if (!this.plants.get(x).get(y-1).isRegistered() && !stack.contains(new Coordinate(x, y-1))) {
                        stack.add(new Coordinate(x, y-1));
                    }
                } else {
                    boundaries.add(Direction.LEFT);
                }
                if (x < (this.plants.size() - 1) && this.plants.get(x + 1).get(y).getType() == currType) {
                    if (!this.plants.get(x+1).get(y).isRegistered() && !stack.contains(new Coordinate(x+1, y))) {
                        stack.add(new Coordinate(x+1, y));
                    }
                } else {
                    boundaries.add(Direction.DOWN);
                }
                if (y < (this.plants.get(x).size() - 1) && this.plants.get(x).get(y+1).getType() == currType) {
                    if (!this.plants.get(x).get(y+1).isRegistered() && !stack.contains(new Coordinate(x, y+1))) {
                        stack.add(new Coordinate(x, y+1));
                    }
                } else {
                    boundaries.add(Direction.RIGHT);
                }
                this.plants.get(x).get(y).setBoundaries(boundaries);
                this.plants.get(x).get(y).markAsRegistered();
            }
            this.regions.add(newRegion);
            first = this.findUnregistered();
        }
    }

    public Coordinate findUnregistered() {
        for (int i = 0; i < this.plants.size(); i++) {
            for (int j = 0; j < this.plants.get(i).size(); j++) {
               if (!this.plants.get(i).get(j).isRegistered()) {
                   return new Coordinate(i,j);
               }
            }
        }
        return new Coordinate(-1,-1);
    }

    public ArrayList<Region> getRegions() {
        return this.regions;
    }

    public ArrayList<ArrayList<Plant>> getPlants() {
        return this.plants;
    }

    public int getTotalPrice() {
        int res = 0;
        for (Region el : this.regions) {
            res += el.getPrice();
        }
        return res;
    }

    public int getTotalPriceUpdated() {
        int res = 0;
        for (Region el : this.regions) {
            res += el.getPriceUpdated();
        }
        return res;
    }
}
