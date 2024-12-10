package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private ArrayList<ArrayList<Integer>> map;
    private HashMap<Coordinate, HashSet<Coordinate>> trailheads;

    public Map (ArrayList<ArrayList<Integer>> map) {
        this.map = map;
        this.trailheads = new HashMap<>();
        ArrayList<Coordinate> trailheadsCoordinates = this.findTrailheads();
        for (Coordinate el : trailheadsCoordinates) {
            this.trailheads.put(el, this.findSummits(el));
        }
    }

    private ArrayList<Coordinate> findTrailheads() {
        ArrayList<Coordinate> res = new ArrayList<>();
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                if (this.map.get(i).get(j) == 0) {
                    res.add(new Coordinate(i,j));
                }
            }
        }
        return res;
    }

    public int findSumScores() {
        int res = 0;
        for (Coordinate trailhead : this.trailheads.keySet()) {
            res += this.trailheads.get(trailhead).size();
        }
        return res;
    }

    private HashSet<Coordinate> findSummits (Coordinate start) {
        int x = start.getFirstCoordinate();
        int y = start.getSecondCoordinate();
        int nextLvl = this.map.get(x).get(y) + 1;
        HashSet<Coordinate> res = new HashSet<>();
        if ((x > 0) && this.map.get(x-1).get(y) == nextLvl) {
            if (nextLvl == 9) {
                res.add(new Coordinate(x-1, y));
            } else {
                res.addAll(findSummits(new Coordinate(x-1, y)));
            }
        }
        if ((x < this.map.size() - 1) && this.map.get(x+1).get(y) == nextLvl) {
            if (nextLvl == 9) {
                res.add(new Coordinate(x+1, y));
            } else {
                res.addAll(findSummits(new Coordinate(x+1, y)));
            }
        }
        if ((y > 0) && this.map.get(x).get(y - 1) == nextLvl) {
            if (nextLvl == 9) {
                res.add(new Coordinate(x, y - 1));
            } else {
                res.addAll(findSummits(new Coordinate(x, y - 1)));
            }
        }

        if ((y < this.map.get(x).size() - 1) && this.map.get(x).get(y + 1) == nextLvl) {
            if (nextLvl == 9) {
                res.add(new Coordinate(x, y + 1));
            } else {
                res.addAll(findSummits(new Coordinate(x, y + 1)));
            }
        }
        return res;
    }





    public HashMap<Coordinate, HashSet<Coordinate>> getTrailheads() {
        return this.trailheads;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                res.append(this.map.get(i).get(j));
            }
            res.append("\n");
        }
        return res.toString();
    }
}
