package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private ArrayList<String> towels;
    private ArrayList<String> patterns;

    public Parser (String fileName) {
        this.towels = new ArrayList<>();
        this.patterns = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (!inputLine.equals("")) {
                String[] words = inputLine.split(",");
                for (String word : words) {
                    this.towels.add(word.trim());
                }
                inputLine = in.readLine();
            }
            inputLine = in.readLine();
            while (inputLine != null) {
                this.patterns.add(inputLine);
                inputLine = in.readLine();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTowels() {
        return this.towels;
    }

    public ArrayList<String> getPatterns() {
        return this.patterns;
    }
}
