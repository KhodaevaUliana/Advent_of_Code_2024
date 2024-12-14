package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private ArrayList<Robot> robots;

    public Parser (String fileName) {
        this.robots = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                String[] positionVelocity = inputLine.split(" ");
                int x = Integer.valueOf(positionVelocity[0].split(",")[0].substring(2));
                int y = Integer.valueOf(positionVelocity[0].split(",")[1]);
                int vx = Integer.valueOf(positionVelocity[1].split(",")[0].substring(2));
                int vy = Integer.valueOf(positionVelocity[1].split(",")[1]);
                this.robots.add(new Robot(x, y, vx, vy));
                inputLine = in.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Robot> getRobots() {
        return this.robots;
    }

}
