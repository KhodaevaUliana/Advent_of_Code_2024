package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private String robotActions;
    private ArrayList<ArrayList<Character>> boardCode;

    public Parser(String fileName) {
        this.boardCode = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (!inputLine.equals("")) {
                ArrayList<Character> newLine = new ArrayList<>();
                for (int i = 0; i < inputLine.length(); i++) {
                    newLine.add(inputLine.charAt(i));
                }
                this.boardCode.add(newLine);
                inputLine = in.readLine();
            }
            inputLine = in.readLine();
            StringBuilder actions = new StringBuilder();
            while (inputLine != null) {
                actions.append(inputLine);
                inputLine = in.readLine();
            }
            this.robotActions = actions.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getRobotActions() {
        return this.robotActions;
    }

    public ArrayList<ArrayList<Character>> getBoardCode() {
        return this.boardCode;
    }
}
