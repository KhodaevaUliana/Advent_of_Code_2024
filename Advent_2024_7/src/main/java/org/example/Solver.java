package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Solver {
    private ArrayList<Long> numbers;
    //private ArrayList<Long> outcomes;
    private Long target;

    public Solver (ArrayList<Long> numbers, Long target) {
        this.numbers = numbers;
        //this.outcomes = new ArrayList<>();
        this.target = target;
        //this.findOutcomes();
    }

    public boolean isOutcome() {
        return recursionStep(this.target, numbers.size() - 1);
    }

    public boolean recursionStep (Long target, int index) {
        if (index > 1) {
            //check multiplication
            long disconcat = disconcatenate(target, this.numbers.get(index));
            if (disconcat == -1) {
                if (target % this.numbers.get(index) == 0) {
                    Long newTargetMult = (Long) target / this.numbers.get(index);
                    Long newTargetAdd = target - this.numbers.get(index);
                    int newIndex = index - 1;
                    return (this.recursionStep(newTargetMult, newIndex) || this.recursionStep(newTargetAdd, newIndex));
                } else {
                    Long newTarget = target - this.numbers.get(index);
                    return this.recursionStep(newTarget, --index);
                }
            } else {
                if (target % this.numbers.get(index) == 0) {
                    Long newTargetMult = (Long) target / this.numbers.get(index);
                    Long newTargetAdd = target - this.numbers.get(index);
                    int newIndex = index - 1;
                    return (this.recursionStep(disconcat, newIndex) || this.recursionStep(newTargetMult, newIndex) || this.recursionStep(newTargetAdd, newIndex));
                } else {
                    Long newTarget = target - this.numbers.get(index);
                    int newIndex = index - 1;
                    return (this.recursionStep(disconcat, newIndex)  ||this.recursionStep(newTarget, newIndex));
                }

            }
        }
        if (index == 1) {
            //check multiplication
            return (concatenate(this.numbers.get(0) ,this.numbers.get(1)) == target || this.numbers.get(1) * this.numbers.get(0) == target || this.numbers.get(1) + this.numbers.get(0) == target);

        }
        System.out.println("No go");
        return true;
    }

    public static long concatenate (long a, long b) {
        StringBuilder build = new StringBuilder();
        build.append(a);
        build.append(b);
        return Long.valueOf(build.toString());
    }

    public static long disconcatenate (long a, long b) {
        String aStr = longToString(a);
        String bStr = longToString(b);
        if (bStr.length() >= aStr.length()) {
            return -1;
        }
        if (aStr.substring(aStr.length() - bStr.length(), aStr.length()).equals(bStr)) {
            return Long.valueOf(aStr.substring(0, aStr.length() - bStr.length()));
        } else {
            return -1;
        }
    }

    private static String longToString (long a) {
        StringBuilder aStrBuild = new StringBuilder();
        aStrBuild.append(a);
        return aStrBuild.toString();
    }



}
