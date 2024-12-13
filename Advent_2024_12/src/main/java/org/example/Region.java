package org.example;

import java.util.ArrayList;
public class Region {
    private ArrayList<Plant> plants;
    private char type;

    public Region (char type) {
        this.type = type;
        this.plants = new ArrayList<>();
    }

    public void add (Plant plant) {
        this.plants.add(plant);
    }

    public int getSize() {
        return this.plants.size();
    }

    public int getPerimeter() {
        int res = 0;
        for (Plant el : this.plants) {
            res += el.getBoundaries().size();
        }
        return res;
    }

    public int countSides() {
        int res = 0;
        int nextSide = this.findNextSide();
        while (nextSide != -1) {
            Direction curr = this.plants.get(nextSide).getBoundariesToReadOff().get(0);
            this.plants.get(nextSide).readOffBoundary(curr);
            int firstInitial = this.plants.get(nextSide).getCoordinate().getFirstCoordinate();
            int secondInitial = this.plants.get(nextSide).getCoordinate().getSecondCoordinate();
            //System.out.println(curr + " " + this.plants.get(nextSide).getCoordinate());
            if (curr.equals(Direction.RIGHT) || curr.equals(Direction.LEFT)) {
                //go up
                int currFirst = firstInitial - 1;
                int index = this.plants.indexOf(new Plant(currFirst, secondInitial));
                while(index != -1 && this.plants.get(index).getBoundaries().contains(curr)) {
                    this.plants.get(index).readOffBoundary(curr);
                    currFirst--;
                    index = this.plants.indexOf(new Plant(currFirst, secondInitial));
                }
                //go down
                currFirst = firstInitial + 1;
                index = this.plants.indexOf(new Plant(currFirst, secondInitial));
                while(index != -1 && this.plants.get(index).getBoundaries().contains(curr)) {
                    this.plants.get(index).readOffBoundary(curr);
                    currFirst++;
                    index = this.plants.indexOf(new Plant(currFirst, secondInitial));
                }
            }
            if (curr.equals(Direction.UP) || curr.equals(Direction.DOWN)) {
                //go left
                int currSecond = secondInitial - 1;
                int index = this.plants.indexOf(new Plant(firstInitial, currSecond));
                while(index != -1 && this.plants.get(index).getBoundaries().contains(curr)) {
                    this.plants.get(index).readOffBoundary(curr);
                    currSecond--;
                    index = this.plants.indexOf(new Plant(firstInitial, currSecond));
                }
                //go right
                currSecond = secondInitial + 1;
                index = this.plants.indexOf(new Plant(firstInitial, currSecond));
                while(index != -1 && this.plants.get(index).getBoundaries().contains(curr)) {
                    this.plants.get(index).readOffBoundary(curr);
                    currSecond++;
                    index = this.plants.indexOf(new Plant(firstInitial, currSecond));
                }
            }
            res++;
            nextSide = this.findNextSide();
        }
        return res;

    }

    public int findNextSide() {
        for (int i = 0; i < this.plants.size(); i++) {
            if (!this.plants.get(i).getBoundariesToReadOff().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public int getPrice() {
        return this.plants.size() * this.getPerimeter();
    }

    public int getPriceUpdated() {
        return this.plants.size() * this.countSides();
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.type);
        res.append(": " + this.getSize() + " plants; perimeter " + this.getPerimeter());
        return res.toString();
    }

}
