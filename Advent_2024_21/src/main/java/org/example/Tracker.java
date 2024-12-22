package org.example;

import java.util.HashMap;

public class Tracker {
    private String initial;
    private HashMap<String, Long> currStat;
    private DirectionalKeypad dir;
    private HashMap<String, String> evolution;
    private long numOfA;

    public Tracker (String initial) {
        this.initial = initial;
        this.currStat = new HashMap<>();
        this.evolution = new HashMap<>();
        this.dir = new DirectionalKeypad();
        this.initializeStat(initial);
    }

    public static long calculateAs (String curr) {
        long cnt = 0;
        for (int i = 0; i < curr.length(); i++) {
            if (curr.charAt(i) == 'A') {
                cnt++;
            }
        }
        return cnt;
    }

    public void initializeStat(String initial) {
        this.numOfA = Tracker.calculateAs(initial);
        String[] splits = initial.split("A");
        for (String split : splits) {
            if (!split.equals("")) {
                if (this.currStat.containsKey(split)) {
                    long currVal = this.currStat.get(split);
                    this.currStat.put(split, currVal + 1L);
                } else {
                    this.currStat.put(split, 1L);
                }
            }
        }
    }
    //calculate A's evolve the rest and cut the last A's

    public void evolve() {
        HashMap<String, Long> newStat = new HashMap<>();
        for (String str : this.currStat.keySet()) {
            long currValOfStr = this.currStat.get(str);
            str += "A";
            String evolvedStr = dir.getOneDirectionToInstruction(str);
            //System.out.println(str + "->" + evolvedStr);
            this.numOfA += (Tracker.calculateAs(evolvedStr) - 1) * currValOfStr;
            String[] splits = evolvedStr.split("A");
            for (String split : splits) {
                if (!split.equals("")) {
                    if (newStat.containsKey(split)) {
                        long currVal = newStat.get(split);
                        newStat.put(split, currVal + currValOfStr);
                    } else {
                        newStat.put(split, currValOfStr);
                    }
                }
            }
        }
        this.currStat = newStat;
    }

    public void printStat() {
        System.out.println(this.currStat);
        System.out.println(this.numOfA);
    }

    public long getLength() {
        long res = 0;
        for (String str : this.currStat.keySet()) {
            res += (long) this.currStat.get(str) * str.length();
        }
        res += this.numOfA;
        return res;
    }
}
