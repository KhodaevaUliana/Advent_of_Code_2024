package org.example;

public class Solution {
    private long movesA;
    private long movesB;

    public Solution (long x, long y) {
        this.movesA = x;
        this.movesB = y;
    }

    public long getCost() {
        return this.movesA * 3 + this.movesB;
    }

    @Override
    public String toString() {
        return this.movesA + ";" + this.movesB;
    }
}
