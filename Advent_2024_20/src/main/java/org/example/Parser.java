package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private ArrayList<ArrayList<Character>> map;
    private Coordinate start;
    private Coordinate finish;

    public Parser(String fileName) {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            int lineCounter = 0;
            while (inputLine != null) {
                ArrayList<Character> newLine = new ArrayList<>();
                for (int j = 0; j < inputLine.length(); j++) {
                    switch (inputLine.charAt(j)) {
                        case 'S':
                            this.start = new Coordinate(lineCounter, j);
                            newLine.add('.');
                            break;
                        case 'E':
                            this.finish = new Coordinate(lineCounter, j);
                            newLine.add('.');
                            break;
                        case '.':
                            newLine.add('.');
                            break;
                        case '#':
                            newLine.add('#');
                            break;
                        default:
                            System.out.println("Unknown character");

                    }

                }
                map.add(newLine);
                inputLine = in.readLine();
                lineCounter++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        this.map = map;

    }

    public ArrayList<ArrayList<Character>> getMap() {
        return this.map;
    }

    public Coordinate getStart() {
        return this.start;
    }

    public Coordinate getFinish() {
        return this.finish;
    }
}
