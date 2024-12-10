package org.example;

import java.util.ArrayList;

public class DiskModified {
    private String diskMap;
    private ArrayList<Integer> fileSystem;
    private ArrayList<Chunk> gaps;
    private ArrayList<Chunk> files;

    public DiskModified (String diskMap) {
        this.diskMap = diskMap;
        this.fileSystem = this.initializeFileSystem();
        this.gaps = new ArrayList<>();
        this.files = new ArrayList<>();
        //gaps and files
        int currChunkStart = 0;
        int currChunkLength = 1;
        int currChunkType = this.fileSystem.get(0);
        for (int i = 1; i < this.fileSystem.size(); i++) {
            if (this.fileSystem.get(i) == currChunkType) {
                currChunkLength++;
            } else {
                if (currChunkType == -1) {
                    this.gaps.add(new Chunk(currChunkStart, currChunkLength));
                } else {
                    this.files.add(new Chunk(currChunkStart, currChunkLength));
                }
                currChunkStart = i;
                currChunkLength = 1;
                currChunkType = this.fileSystem.get(i);
            }
        }
        if (currChunkType == -1) {
            this.gaps.add(new Chunk(currChunkStart, currChunkLength));
        } else {
            this.files.add(new Chunk(currChunkStart, currChunkLength));
        }
    }

    public ArrayList<Chunk> getGaps() {
        return this.gaps;
    }

    public ArrayList<Chunk> getFiles() {
        return this.files;
    }

    public void optimize() {
        for (int i = this.files.size() - 1; i >= 0; i--) {
            int requestedSize = this.files.get(i).getLength();
            int index = 0;
            boolean placeFound = false;
            while (index < this.gaps.size()) {
                if (this.gaps.get(index).getLength() >= requestedSize && this.gaps.get(index).getPosition() < this.files.get(i).getPosition()) {
                    placeFound = true;
                    break;
                }
                index++;
            }
            if (placeFound) {
                this.files.get(i).move(this.gaps.get(index).getPosition());
                int newGapSize =  this.gaps.get(index).getLength() - requestedSize;
                int newGapPosition = this.gaps.get(index).getPosition() + requestedSize;
                this.gaps.get(index).changeSize(newGapSize);
                this.gaps.get(index).move(newGapPosition);
            }
            //this.upDateFileSystem();
            //System.out.println(this.fileSysString());
        }
        this.upDateFileSystem();

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

    public long getCheckSum() {
        long res = 0;
        for (int i = 0; i < this.files.size(); i++) {
            int length = this.files.get(i).getLength();
            int start = this.files.get(i).getPosition();
            for (int j = start; j < start + length; j++) {
                //System.out.println(i + "added" + j * i);
                res += (long) j * i;
            }
        }
        return res;
    }

    public void upDateFileSystem() {
        ArrayList<Integer> newFS = new ArrayList<>();
        for (int i = 0; i < this.fileSystem.size(); i++) {
            newFS.add(-1);
        }
        for (int  i = 0; i < this.files.size(); i++) {
            int start = this.files.get(i).getPosition();
            int length = this.files.get(i).getLength();
            for (int j = start; j < start + length; j++) {
                newFS.set(j, i);
            }
        }
        this.fileSystem = newFS;

    }


}
