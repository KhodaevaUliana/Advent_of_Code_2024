package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Maze {
    private  ArrayList<ArrayList<Character>> map;
    private ArrayList<Coordinate> vertices; //info about possible turn points
    private ArrayList<ArrayList<Integer>> connectivity; //graph info
    private ArrayList<ArrayList<Integer>> bestPathPrev; //helper structure for Dijkstra
    private Coordinate start;
    private Coordinate finish;//best paths tracker

    public Maze(ArrayList<ArrayList<Character>> map, Coordinate start, Coordinate finish) {
        this.map = map;
        this.start = start;
        this.finish = finish;
        this.vertices = new ArrayList<>();
        this.vertices.add(this.start);//start at the beginning
        this.vertices.add(this.finish); //finish at the end
        //other vertices in between
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                Coordinate curr = new Coordinate(i, j);
                if (this.isTurningPoint(i,j) && !curr.equals(this.start) && !curr.equals(this.finish)) {
                    this.vertices.add(1, curr);
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
                    /*if (distFromIndInQuestion  == distancesFromStart.get(i)) {
                        //System.out.println("Alternative path: " + this.vertices.get(nextIndex) + " " + this.vertices.get(i));
                        prev.get(i).add(nextIndex);
                    }*/
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

    public ArrayList<Coordinate> addEdge(Coordinate A, Coordinate B) {
        ArrayList<Coordinate> edge = new ArrayList<>();
        if (A.getFirst() == B.getFirst()) {
            int start = A.getSecond();
            int finish = B.getSecond();
            if (finish > start) {
                for (int j = start + 1; j < finish; j++) {
                    edge.add(new Coordinate(A.getFirst(), j));
                }
            } else {
                for (int j = start - 1; j > finish; j--) {
                    edge.add(new Coordinate(A.getFirst(), j));
                }
            }
        }
        if (A.getSecond() == B.getSecond()) {
            int start = A.getFirst();
            int finish = B.getFirst();
            if (start < finish) {
                for (int i = start + 1; i < finish; i++) {
                    edge.add(new Coordinate(i, A.getSecond()));
                }
            } else {
                for (int i = start - 1; i > finish; i--) {
                    edge.add(new Coordinate(i, A.getSecond()));
                }
            }

        }
        return edge;
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

    public void findShortcuts() {

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

    public ArrayList<Coordinate> getBestPath() {
        int currIndex = this.vertices.size() - 1;
        Coordinate startEdge = this.finish;
        Coordinate finishEdge = this.finish;
        ArrayList<Coordinate> pathReversed = new ArrayList<>();
        pathReversed.add(finishEdge);
        ArrayList<Integer> prev = this.bestPathPrev.get(currIndex);
        while (!prev.isEmpty() && !finishEdge.equals(this.start)) {
            startEdge = finishEdge;
            currIndex = prev.get(0);
            finishEdge = this.vertices.get(currIndex);
            //add vertices belonging to this edge
            pathReversed.addAll(this.addEdge(startEdge, finishEdge));
            pathReversed.add(finishEdge);
            prev = this.bestPathPrev.get(currIndex);
        }
        //invert
        ArrayList<Coordinate> path = new ArrayList<>();
        for (int i = pathReversed.size() - 1; i >= 0; i--) {
            path.add(pathReversed.get(i));
        }
        return path;
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
        return length;
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

    public void printPrev() {
        for (int i = 0; i < this.vertices.size(); i++) {
            System.out.println(i + ": " + this.bestPathPrev.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).size(); j++) {
                Coordinate curr = new Coordinate(i,j);
                if (curr.equals(this.start)) {
                    res.append('S');
                } else if (curr.equals(this.finish)) {
                    res.append('E');
                } else {
                    res.append(this.map.get(i).get(j));
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
