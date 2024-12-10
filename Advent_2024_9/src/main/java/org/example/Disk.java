package org.example;
import java.util.ArrayList;

public class Disk {
    private String diskMap;
    private ArrayList<Integer> fileSystem;
    private ArrayList<Integer> gapsPositions;

    public Disk (String diskMap) {
        this.diskMap = diskMap;
        this.fileSystem = this.initializeFileSystem();
        this.gapsPositions = this.initializeGapPositions();
    }

    private ArrayList<Integer> initializeFileSystem() {
        ArrayList<Integer> fileSystem = new ArrayList<>();
        for (int i = 0; i < this.diskMap.length(); i++) {
            if (i % 2 == 0) {
                int id = (i / 2);
                for (int j = 0; j < Character.getNumericValue(this.diskMap.charAt(i)); j++) {
                    fileSystem.add(id);
                }
            } else {
                for (int j = 0; j < Character.getNumericValue(this.diskMap.charAt(i)); j++) {
                    fileSystem.add(-1);
                }
            }
        }
        return fileSystem;
    }

    private ArrayList<Integer> initializeGapPositions() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.fileSystem.size(); i++) {
            if (this.fileSystem.get(i) == -1) {
                res.add(i);
            }
        }
        return res;
    }

    public int calculateGaps() {
       return this.gapsPositions.size();
    }


    public String fileSysString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.fileSystem.size(); i++) {
            if (this.fileSystem.get(i) == -1) {
                res.append(".");
            } else {
                res.append(this.fileSystem.get(i));
            }
        }
        return res.toString();
    }

    public void reorganize() {
        int pointer = this.fileSystem.size() - 1;
        while (this.fileSystem.get(pointer) == -1) {
            pointer--;
        }
        while (!(this.gapsPositions.isEmpty()) && pointer > this.gapsPositions.get(0)) {
            //System.out.println(this.gapsPositions);
            int gapIndex = this.gapsPositions.get(0);
            this.fileSystem.set(gapIndex, this.fileSystem.get(pointer));
            this.fileSystem.set(pointer, -1);
            this.gapsPositions.remove(0);
            while (this.fileSystem.get(pointer) == -1) {
                pointer--;
            }
            //System.out.println(this.fileSysString());
        }
    }

    public long getCheckSum() {
        int index = 0;
        long res = 0;
        while (this.fileSystem.get(index) != -1) {
            res += (long) this.fileSystem.get(index) * index;
            index++;
        }
        return res;
    }
}
