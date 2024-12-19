package org.example;

import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class Solver {
    private ArrayList<String> towels;
    private String pattern;
    private boolean[] matchNotFound;
    private boolean isPattern;
    private int numOfDesigns;


    public Solver(ArrayList<String> towels, String pattern) {
        this.towels = towels;
        this.pattern = pattern;
        this.matchNotFound = new boolean[pattern.length()];
        this.isPattern = false;
        this.numOfDesigns = 0;
    }

    //recursive solution to the 1st part
    public boolean isPossiblePattern() {
        this.isMatchFound(0);
        return this.isPattern;
    }

    //helper function for the recursive solution to the 1st part
    public void isMatchFound(int currIndex) {
        String subPattern = this.pattern.substring(currIndex);
        for (String towel : this.towels) {
            if (subPattern.indexOf(towel) == 0) {
                //System.out.println(towel + " " + currIndex);
                if (currIndex + towel.length() == this.pattern.length()) {
                    this.isPattern = true;
                    return;
                } else {
                    int newCurrIndex = currIndex + towel.length();
                    if (!this.matchNotFound[newCurrIndex]) {
                        isMatchFound(newCurrIndex);
                    }
                }
            }
        }
        this.matchNotFound[currIndex] = true;
    }

    //Dynamic programming solution to the 2nd part
    public long numOfDesigns () {
        //nums[i] is the number of designs for a substring(0,i) of the pattern
        long[] nums = new long[this.pattern.length() + 1];
        nums[0] = 0L;
        for (int currLength = 1; currLength <= this.pattern.length(); currLength++) {
            for (String towel : this.towels) {
                if (towel.length() <= currLength) {
                    String currSubpattern = this.pattern.substring(currLength - towel.length(), currLength);
                    if (currSubpattern.equals(towel)) {
                        if (currLength == towel.length()) {
                            nums[currLength] += 1L;
                        } else {
                            nums[currLength] += nums[currLength - towel.length()];
                        }
                    }

                }
            }
        }
        return nums[this.pattern.length()];
    }




}
