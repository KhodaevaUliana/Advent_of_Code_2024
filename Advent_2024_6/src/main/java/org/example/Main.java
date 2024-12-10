package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Main {
    /*06.12*/
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> boardCode = new ArrayList<>();

        try {
            String fileName = "src/advent_6.txt";
            //String fileName = "src/test.txt";
            try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
                String inputLine = in.readLine();
                while (inputLine != null) {
                    ArrayList<Character> newLine = new ArrayList<>();
                    for (int i = 0; i < inputLine.length(); i++) {
                        newLine.add(inputLine.charAt(i));
                    }
                    boardCode.add(newLine);
                    inputLine = in.readLine();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            //String fileName = "src/test.txt";

        } catch (Exception e) {
            e.printStackTrace();
        }
        int cycles = 0;
        Board garden = new Board (boardCode);
        for (int i = 0; i < boardCode.size(); i++) {
            for (int j = 0; j < boardCode.size(); j++) {
                garden = new Board (boardCode);
                if (garden.setTrap(i, j)) {
                    boolean isCycle = garden.walk();
                    if (garden.walk()) {
                        cycles += 1;
                    }
                }
            }
        }
        System.out.println(cycles);


        /*Board garden = new Board (boardCode);
        System.out.println(garden.printBoard());
        System.out.println(garden.walk());
        System.out.println(garden.printBoard());
        System.out.println(garden.calculateVisited());*/



    }



}