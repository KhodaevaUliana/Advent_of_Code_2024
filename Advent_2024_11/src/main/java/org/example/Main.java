package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your puzzle input");
        ArrayList<Long> stones = stringToNums(scanner.nextLine());
        System.out.println("Enter the number of blinks");
        int numOfBlinks = Integer.valueOf(scanner.nextLine());
        StonesTwo myStones = new StonesTwo (stones);
        for (int i = 0; i < numOfBlinks; i++) {
            myStones.blink();
        }
        System.out.println(myStones.getNumberOfStones());
        /*The key to getting results for large numbers of blinks (e.g., 75) is to save
        the information now into an array of stones (though it is the most intuitive approach)
        but into a HashMap with distinct digits as keys and the number of occurrences of
        this digit as values. It turns out that the array of stones has a lot of repetitive digits,
        and while the size of the array grows exponentially, the number of _distinct_ values
        grows linearly. This observation helps to optimize the solution and obtain results in O(n).
         */





    }

    public static ArrayList<Long> stringToNums (String input) {
        String[] numArr = input.split(" ");
        ArrayList<Long> res = new ArrayList<>();
        for (String el : numArr) {
            res.add(Long.valueOf(el));
        }
        return res;
    }



}