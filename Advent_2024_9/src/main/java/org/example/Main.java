package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String diskMap = "";
        try {
            String fileName = "src/advent_9.txt";
            //String fileName = "src/test.txt";
            try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
                diskMap = in.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiskModified disk = new DiskModified (diskMap);
        //System.out.println(disk.fileSysString());
        //System.out.println("Files:");
        //System.out.println(disk.getFiles());
        //System.out.println("Gaps:");
        //System.out.println(disk.getGaps());
        disk.optimize();
        //System.out.println("Files:");
        //System.out.println(disk.getFiles());
        //System.out.println(disk.fileSysString());
        System.out.println(disk.getCheckSum());






    }
}