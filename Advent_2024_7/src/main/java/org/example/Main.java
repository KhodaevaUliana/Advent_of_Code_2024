package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> tasks = new ArrayList<>();
        ArrayList<Long> targets = new ArrayList<>();

        try {
            String fileName = "src/advent_7.txt";
            //String fileName = "src/test.txt";
            Parser parser = new Parser(fileName);
            tasks = parser.getTasks();
            targets = parser.getTargets();


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("reading finished");
        int numValid = 0;
        long res = 0;
        int all = 0;
        for (int i = 0; i < targets.size(); i++) {
            all += 1;
            Long key = targets.get(i);
            ArrayList<Long> value = tasks.get(i);
            if (key < 0) {
                System.out.println("Key error!");
            }
           // System.out.println("Key: " + key + ", Value: " + value);
            Solver solver = new Solver(value, key);
            //System.out.println(solver.disconcatenate(111, 111));
            if (solver.isOutcome()) {
                numValid += 1;
                res += key;
                //System.out.println("Key: " + key + ", Value: " + value);
            }

        }
        System.out.println("All: " + all);
        System.out.println("Valid: " + numValid);
        System.out.println("Result: " + res);



    }
}