package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/advent_12.txt";
        //String fileName = "src/test.txt";
        ArrayList<ArrayList<Character>> map = getMap(fileName);
        Garden garden = new Garden(map);
        garden.register();
        System.out.println("The answer to Part I:");
        System.out.println(garden.getTotalPrice()); //The answer to Part I
        System.out.println("The answer to Part II:");
        System.out.println(garden.getTotalPriceUpdated()); //The answer to Part II



    }

    public static ArrayList<ArrayList<Character>> getMap (String fileName) {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();

        try {
            try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
                String inputLine = in.readLine();
                while (inputLine != null) {
                    ArrayList<Character> newLine = new ArrayList<>();
                    for (int i = 0; i < inputLine.length(); i++) {
                        newLine.add(inputLine.charAt(i));
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