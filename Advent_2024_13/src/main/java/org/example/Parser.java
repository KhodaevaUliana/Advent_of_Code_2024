package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
    private ArrayList<Task> tasks;

    public Parser (String fileName) {
        this.tasks = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                if (inputLine.equals("")) {
                    inputLine = in.readLine();
                }
                //read off button A info
                String[] firstLine = inputLine.split(" ");
                int ax = Integer.valueOf(firstLine[2].substring(2, firstLine[2].length() - 1));
                int ay = Integer.valueOf(firstLine[3].substring(2, firstLine[3].length()));
                //read off button B info
                inputLine = in.readLine();
                String[] secondLine = inputLine.split(" ");
                int bx = Integer.valueOf(secondLine[2].substring(2, secondLine[2].length() - 1));
                int by = Integer.valueOf(secondLine[3].substring(2, secondLine[3].length()));
                //read off prize info
                inputLine = in.readLine();
                String[] thirdLine = inputLine.split(" ");
                int x = Integer.valueOf(thirdLine[1].substring(2, thirdLine[1].length() - 1));
                int y = Integer.valueOf(thirdLine[2].substring(2, thirdLine[2].length()));
                Task curr = new Task(ax, ay, bx, by, x + 10000000000000L, y + 10000000000000L);
                //System.out.println(curr);
                this.tasks.add(curr);
                //read off blank line
                inputLine =  in.readLine();


                inputLine = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }



}
