package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter codes (line by line) and at the end press enter 2 times:");
        String nextLine = scanner.nextLine();
        ArrayList<String> codes = new ArrayList<>();
        while (!nextLine.isEmpty()) {
            codes.add(nextLine);
            nextLine = scanner.nextLine();
        }
        System.out.println("Enter number of iterations (2 for Part I or 25 for Part II");
        int numOfIterations = Integer.valueOf(scanner.nextLine());
        long res = 0;
        for (String code : codes) {
            res += getComplexityUpd(code, numOfIterations);
        }
        System.out.println("The answer:");
        System.out.println(res);



    }

    public static long getComplexityUpd(String code, int numIter) {
        NumKeypad numKeypad = new NumKeypad();
        ArrayList<String> options = numKeypad.getInstructionToCode(code);
        ArrayList<Long> optionRes = new ArrayList<>();
        for (String option : options) {
            //System.out.println(option);
            Tracker tracker = new Tracker(option);
            for (int i = 0; i < numIter; i++) {
                tracker.evolve();
            }
            optionRes.add(tracker.getLength());
        }
        long min = optionRes.get(0);
        for (long currOptionRes : optionRes) {
            if (currOptionRes < min) {
                min = currOptionRes;
            }
        }
        int numPart = Integer.valueOf(code.substring(0, code.length() - 1));
        return min * numPart;
    }




}