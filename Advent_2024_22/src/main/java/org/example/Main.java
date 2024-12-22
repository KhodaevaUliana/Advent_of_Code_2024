package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_22.txt";
        ArrayList<Long> input = readOffNumbers(fileName);
        SecretGenerator secgen = new SecretGenerator();
        //Part I
        long res = 0;
        for (int i = 0; i < input.size(); i++) {
            long currSecret = input.get(i);
            for (int j = 0; j < 2000; j++) {
                currSecret = secgen.generateNewSecret(currSecret);
            }
            //System.out.println(currSecret);
            res += currSecret;
        }
        System.out.println("Part I:");
        System.out.println(res);

        //Part II
        Solver solver = new Solver(input);
        solver.initializeSequencePrice();
        System.out.println("Part II:");
        System.out.println(solver.findNumOfBananas());






    }

    public static ArrayList<Long> readOffNumbers (String fileName) {
        ArrayList<Long> nums = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                nums.add(Long.valueOf(inputLine));
                inputLine = in.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return nums;
    }
}