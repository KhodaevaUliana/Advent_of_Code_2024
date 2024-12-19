package org.example;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //String fileName = "src/test.txt";
        String fileName = "src/advent_19.txt";
        Parser parser = new Parser(fileName);
        ArrayList<String> patterns = parser.getPatterns();

        //Part I
        int res = 0;
        for (String pattern : patterns) {
            Solver solver = new Solver(parser.getTowels(), pattern);
            if (solver.isPossiblePattern()) {
                res += 1;
            }
        }
        System.out.println("Part I (number of possible designs):");
        System.out.println(res);
        long overallDesigns = 0;
        //Part II
        for (String pattern : patterns) {
            Solver solver = new Solver(parser.getTowels(), pattern);
            long numOfDesigns = (long) solver.numOfDesigns();
            overallDesigns += numOfDesigns;
        }
        System.out.println("Part II (the total number of different designs):");
        System.out.println(overallDesigns);


    }
}