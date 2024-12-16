package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_16.txt";
        ArrayList<ArrayList<Character>> map = readMap(fileName);
        Maze maze = new Maze(map);
        System.out.println("Part I (the score of the best path):");
        System.out.println(maze.findBestPath()); //Answer to Part I
        maze.drawBestPaths(); //doesn't print anything but marks with 'O''s the best paths
        //System.out.println(maze); //visualization
        System.out.println("Part II (the number of tiles):");
        System.out.println(maze.calculateTiles()); //Answer to Part II



    }

    public static ArrayList<ArrayList<Character>> readMap (String fileName) {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                ArrayList<Character> newLine = new ArrayList<>();
                for (int i = 0; i < inputLine.length(); i++) {
                    if (inputLine.charAt(i) == 'E' || inputLine.charAt(i) == 'S') {
                        newLine.add('.');
                    } else {
                        newLine.add(inputLine.charAt(i));
                    }
                }
                map.add(newLine);
                inputLine = in.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}