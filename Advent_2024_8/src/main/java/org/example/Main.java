package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/advent_8.txt";
        //String fileName = "src/test.txt";
        ArrayList<ArrayList<Character>> boardCode = getInput(fileName);
        Map map = new Map (boardCode);
        //System.out.println(map);
        //System.out.println(map.getAntennas());
        //map.setAntinodes();
        map.setNewAntinodes();
        //System.out.println(map);
        System.out.println(map.calculateAntinodes());


    }

    public static int gcd (int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }



    public static ArrayList<ArrayList<Character>> getInput(String fileName) {
        ArrayList<ArrayList<Character>> boardCode = new ArrayList<>();
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardCode;
    }
}