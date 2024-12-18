package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*String testFileName = "src/test.txt";
        ArrayList<Coordinate> testCorrupted = readOffCorruptedBytes(testFileName);
        int testSize = 7;
        int testNumOfCorruptedBits = 21;
        ArrayList<Coordinate> testCorruptedPart = new ArrayList<>(testCorrupted.subList(0, testNumOfCorruptedBits));
        Maze testMaze = new Maze(testCorruptedPart, testSize);
        System.out.println(testMaze);
        System.out.println(testMaze.findBestPath());*/
        //testMaze.drawBestPaths();
        //System.out.println(testMaze);

        String fileName = "src/advent_18.txt";
        ArrayList<Coordinate> corrupted = readOffCorruptedBytes(fileName);
        int size = 71;
        int numOfCorruptedBits = 1024;
        ArrayList<Coordinate> corruptedPart = new ArrayList<>(corrupted.subList(0, numOfCorruptedBits));
        Maze maze = new Maze(corruptedPart, size);
        System.out.println("Part I: ");
        System.out.println(maze.findBestPath());//the answer to the 1st part
        // binary search
        int left = numOfCorruptedBits;
        int right = corrupted.size();
        while (right - left > 1) {
            int mid = (left + right) / 2;
            corruptedPart = new ArrayList<>(corrupted.subList(0, mid));
            maze = new Maze(corruptedPart, size);
            if (maze.findBestPath() == -1) {
                right = mid;
            } else {
                left = mid;
            }

        }
        System.out.println("Part II:");
        System.out.println(corrupted.get(right - 1));//the answer to the 2nd part


    }

    public static ArrayList<Coordinate> readOffCorruptedBytes (String fileName) {
        ArrayList<Coordinate> corrupted = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                String[] nums = inputLine.split(",");
                corrupted.add(new Coordinate(Integer.valueOf(nums[1]),Integer.valueOf(nums[0])));
                inputLine = in.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return corrupted;
    }
}