package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_20.txt";
        Parser parser = new Parser(fileName);
        Maze maze = new Maze(parser.getMap(), parser.getStart(), parser.getFinish());
        maze.findBestPath();
        ArrayList<Coordinate> bestPath = maze.getBestPath();
        //Part I
        ShortcutsFinder finder = new ShortcutsFinder(bestPath);
        ArrayList<Shortcut> shortcuts = finder.getShortcuts();
        //Collections.sort(shortcuts, (Shortcut a,Shortcut b) -> a.compareTo(b));
        //System.out.println(shortcuts);
        int threshold = 100;
        int cnt = 0;
        for (Shortcut shortcut :shortcuts) {
            if (shortcut.getSaved() >= threshold) {
                cnt++;
            }
        }
        System.out.println("Part I:");
        System.out.println(cnt);

        //Part ||
        HashMap<Integer, ArrayList<Shortcut>> stat = finder.getShortcutsNewStat();
        /*for (int saved : stat.keySet()) {
            if (saved >= 50) {
                System.out.println("There are " + stat.get(saved).size() + " shortcuts that save " + saved);
            }
        }*/

        long cntNew = 0;
        for (int saved : stat.keySet()) {
            if (saved >= threshold) {
                cntNew +=  stat.get(saved).size();
            }
        }
        System.out.println("Part II:");
        System.out.println(cntNew);



    }


}