package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/advent_10.txt";
        //String fileName = "src/test.txt";
        ArrayList<ArrayList<Integer>> mapCode = getMap(fileName);
        Map map = new Map (mapCode);
        //System.out.println(map);
        //System.out.println(map.getTrailheads());
        //System.out.println(map.findSummits(new Coordinate(0,2)));

        //System.out.println(map);
        System.out.println(map.findSumScores()); //Solution to BOTH Part I and Part II
        /*To get the answer to Part I (aka the number of reachable summits),
        go to Coordinate class and comment out overriden methods
        Coordinate.equals() and Coordinate.hashCode().

        To get the answer to Part II (aka the number of distinct paths leading
        to all reachable summits), don't do anything
        aka do not override Coordinate.equals() and Coordinate.hashCode().

         */
    }


    public static ArrayList<ArrayList<Integer>> getMap (String fileName) {
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();

        try {
            try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
                String inputLine = in.readLine();
                while (inputLine != null) {
                    ArrayList<Integer> newLine = new ArrayList<>();
                    for (int i = 0; i < inputLine.length(); i++) {
                        newLine.add(Character.getNumericValue(inputLine.charAt(i)));
                    }
                    map.add(newLine);
                    inputLine = in.readLine();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
}