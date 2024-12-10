package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private String fileName;
    private ArrayList<Long> targets;
    private ArrayList<ArrayList<Long>> tasks;

    public Parser(String fileName) {
        this.fileName = fileName;
        this.targets = new ArrayList<>();
        this.tasks = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            int cntLines = 0;
            while (inputLine != null) {
                cntLines += 1;
                String[] data = inputLine.split(": ");
                if (data[0].length() > 18) {
                    System.out.println("Achtung!");
                }
                Long target = Long.valueOf(data[0]);
                String[] nums = data[1].split(" ");
                ArrayList<Long> arr = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    arr.add(Long.valueOf(nums[i]));
                }
                this.tasks.add(arr);
                this.targets.add(target);
                inputLine = in.readLine();

            }
            System.out.println(cntLines);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Long>> getTasks() {
        return this.tasks;
    }

    public ArrayList<Long> getTargets() {
        return this.targets;
    }



}
