package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_24.txt";
        Device device = new Device(fileName);
        device.run();
        long z = device.extractZ();
        System.out.println("Part I (the output on the wires starting with z):");
        System.out.println(z);
        System.out.println("Part II (the wires to be swapped)");
        System.out.println(device.getWiresToBeSwapped());

    }
}