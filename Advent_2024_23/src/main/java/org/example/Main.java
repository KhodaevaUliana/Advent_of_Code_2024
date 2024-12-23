package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_23.txt";
        NetworkLists network = new NetworkLists(fileName);
        //System.out.println(network.calculateConnectedTriples());
        System.out.println("Part I:");
        System.out.println(network.calculateConnectedTriplesWithT());
        ArrayList<String> maxClique = network.findMaxClique();
        maxClique.sort(null);
        String maxCliqueString = String.join(",", maxClique);
        System.out.println("Part I (the size of the largest clique):");
        System.out.println(maxCliqueString);

    }
}