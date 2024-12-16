package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Maze {
    private  ArrayList<ArrayList<Character>> map;
    private ArrayList<Coordinate> vertices; //info about possible turn points
    private ArrayList<ArrayList<Integer>> connectivity; //graph info
    private ArrayList<ArrayList<Integer>> bestPathPrev; //best paths tracker

    public Maze(ArrayList<ArrayList<Character>> map) {
        this.map = map;
        this.vertices = new ArrayList<>();
        this.vertices.add(new Coordinate(this.map.size() - 2, 1));//start
        this.vertices.add(new Coordinate(1, this.map.get(1).size() - 2)); //finish
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                if (this.isTurningPoint(i,j) && !(i == this.map.size() - 2 && j == 1) && !(i == 1 && j == this.map.get(1).size() - 2)) {
                    this.vertices.add(1, new Coordinate(i, j));
                }
            }
        }
        this.connectivity = new ArrayList<>();
        for (int i = 0; i < this.vertices.size(); i++) {
            ArrayList<Integer> newLine = new ArrayList<>();
            for (int j = 0; j < this.vertices.size(); j++) {
                newLine.add(this.thereISEdge(this.vertices.get(i), this.vertices.get(j)));
            }
            this.connectivity.add(newLine);
        }
        //System.out.println(this.vertices);
        //System.out.println(this.connectivity);
    }

    //some old good Dijkstra + array 'prev' to track the best paths
    public int findBestPath() {
        ArrayList<Integer> distancesFromStart = new ArrayList<>();
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>(); //previous nodes
        HashSet<Integer> unvisited = new HashSet<>();
        for (int i = 0; i < this.vertices.size(); i++) {
            unvisited.add(i);
            prev.add(new ArrayList<>());
        }
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.connectivity.get(0).get(i) == 0) {
                distancesFromStart.add(-1);
            } else {
                distancesFromStart.add(this.connectivity.get(0).get(i));
                prev.get(i).add(0);
            }
        }
        unvisited.remove(0);
        //System.out.println(distancesFromStart);
        while (!unvisited.isEmpty()) {
            int nextIndex = -1;
            int min = -1;
            //SELECT the closest to the start FROM unvisited
            for (int i : unvisited) {
                if (distancesFromStart.get(i) != -1) {
                    if ((min != -1 && distancesFromStart.get(i) < min) || min == -1) {
                        min = distancesFromStart.get(i);
                        nextIndex = i;
                    }
                }
            }
            //in case we are stuck
            if (nextIndex == -1) {
                break;
            }
            //update distances
            for (int i = 0; i < this.vertices.size(); i++) {
                if (this.connectivity.get(nextIndex).get(i) != 0) {
                    int distFromIndInQuestion = distancesFromStart.get(nextIndex) + this.connectivity.get(nextIndex).get(i);
                    if (distFromIndInQuestion  == distancesFromStart.get(i)) {
                        //System.out.println("Alternative path: " + this.vertices.get(nextIndex) + " " + this.vertices.get(i));
                        prev.get(i).add(nextIndex);
                    }
                    if (distancesFromStart.get(i) == -1 || distFromIndInQuestion  < distancesFromStart.get(i)) {
                        if (distFromIndInQuestion  < distancesFromStart.get(i)) {
                            //System.out.println("Better path found " +  this.vertices.get(nextIndex) + " " + this.vertices.get(i));
                            prev.set(i, new ArrayList<>());
                        }
                        distancesFromStart.set(i, distFromIndInQuestion);
                        prev.get(i).add(nextIndex);

                    }
                }
            }
            unvisited.remove(nextIndex);
            //System.out.println(nextIndex);
            //System.out.println(distancesFromStart);
        }
        this.bestPathPrev = prev;
        return distancesFromStart.get(this.vertices.size() - 1);

    }
    public ArrayList<ArrayList<Integer>> getPrev() {
        return this.bestPathPrev;
    }

    public ArrayList<Coordinate> getVertices() {
        return this.vertices;
    }

    //helper function: marks a path between to vertices with O's
    public void connectVertices(Coordinate A, Coordinate B) {
        if (A.getFirst() == B.getFirst()) {
            int start = A.getSecond();
            int finish = B.getSecond();
            if (A.getSecond() > B.getSecond()) {
                start = B.getSecond();
                finish = A.getSecond();
            }
            for (int j = start; j <= finish; j++) {
                this.map.get(A.getFirst()).set(j, 'O');
            }
        }
        if (A.getSecond() == B.getSecond()) {
            int start = A.getFirst();
            int finish = B.getFirst();
            if (A.getFirst() > B.getFirst()) {
                start = B.getFirst();
                finish = A.getFirst();
            }
            for (int i = start; i <= finish; i++) {
                ArrayList<Character> oldLine = this.map.get(i);
                oldLine.set(A.getSecond(), 'O');
                this.map.set(i, oldLine);
            }

        }

    }

    //recursively draw the best path to the start
    public void drawPathsToPrev(int index) {
        for (int j = 0; j < this.bestPathPrev.get(index).size(); j++) {
            int newIndex = this.bestPathPrev.get(index).get(j);
            this.connectVertices(this.vertices.get(index), this.vertices.get(this.bestPathPrev.get(index).get(j)));
            if (newIndex > 0 && newIndex < this.vertices.size()) {
                drawPathsToPrev(newIndex);
            }
        }
    }

    //calls recursive function drawPathsToPrev() for the finish vertex
    public void drawBestPaths() {
       drawPathsToPrev(this.vertices.size() - 1);
    }

    public int calculateTiles() {
        int res = 0;
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                if (this.map.get(i).get(j) == 'O') {
                    res += 1;
                }
            }
        }
        return res;
    }

    //determine whether there is and edge between two vertices
    public int thereISEdge (Coordinate A, Coordinate B) {
        if ((A.getFirst() != B.getFirst()) && (A.getSecond() != B.getSecond())) {
            return 0;
        }
        if ((A.getFirst() == B.getFirst()) && (A.getSecond() == B.getSecond())) {
            return 0;
        }
        int length = 1;
        if (A.getFirst() == B.getFirst()) {
            int start = A.getSecond();
            int finish = B.getSecond();
            if (A.getSecond() > B.getSecond()) {
                start = B.getSecond();
                finish = A.getSecond();
            }
            while ((start + length < finish) && this.map.get(A.getFirst()).get(start + length) == '.') {
                length++;
            }
            if (start + length < finish) {
                return 0;
            }
        }
        if (A.getSecond() == B.getSecond()) {
            int start = A.getFirst();
            int finish = B.getFirst();
            if (A.getFirst() > B.getFirst()) {
                start = B.getFirst();
                finish = A.getFirst();
            }
            while ((start + length < finish) && this.map.get(start + length).get(A.getSecond()) == '.') {
                length++;
            }
            if (start + length < finish) {
                return 0;
            }
        }
        if (((A.getFirst() == this.map.size() - 2 && A.getSecond() == 1) || (B.getFirst() == this.map.size() - 2 && B.getSecond() == 1)) && (A.getFirst() == B.getFirst())) {
            return length;
        }
        return (length + 1000);
    }

    //detect turning points
    public boolean isTurningPoint(int i, int j) {
        if (this.map.get(i).get(j) != '.') {
            return false;
        }
        //mirrored L
        if (i > 0 && j > 0 && this.map.get(i-1).get(j) == '.' && this.map.get(i).get(j-1) == '.') {
            return true;
        }
        //normal L
        if (i > 0 && j < (this.map.get(i).size() - 1) && this.map.get(i-1).get(j) == '.' && this.map.get(i).get(j+1) == '.') {
            return true;
        }
        //normal Г
        if (i < (this.map.size() - 1) && j < (this.map.get(i).size() - 1) && this.map.get(i+1).get(j) == '.' && this.map.get(i).get(j+1) == '.') {
            return true;
        }
        //mirrored Г
        if (i < (this.map.size() - 1) && j > 0 && this.map.get(i+1).get(j) == '.' && this.map.get(i).get(j-1) == '.') {
            return true;
        }
        return false;

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
