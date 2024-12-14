package org.example;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*String fileNameTest = "src/test.txt";
        Parser parserTest = new Parser(fileNameTest);
        ArrayList<Robot> robotsTest = parserTest.getRobots();
        Board testBoard = new Board(11, 7, robotsTest);
        testBoard.evolve(100);
        System.out.println(testBoard);
        System.out.println(testBoard.getSafetyFactor());*/
        String fileName = "src/advent_14.txt";
        Parser parser = new Parser(fileName);
        ArrayList<Robot> robots = parser.getRobots();
        Board actualBoard = new Board(101, 103, robots);
        //The two lines below solve the first part
        /*actualBoard.evolve(100);
        System.out.println(actualBoard.getSafetyFactor());*/
        //Part II
        for (int i = 0; i < 10000; i++) {
            actualBoard.evolve(1);
            //we just look for a single long horizontal line as a hallmark of the Christmas Tree
            if (actualBoard.findAHorizontalLine(15)) {
                System.out.println("Suspicious!");
                break;
            }

        }
        System.out.println(actualBoard);
        System.out.println(actualBoard.getTime());

    }
}