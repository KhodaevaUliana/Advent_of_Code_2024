package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class StonesTwo {
    private HashMap<Long, Long> stones; //Key: specific digit Value: number of instances

    public StonesTwo (ArrayList<Long> stones) {
        this.stones = new HashMap<>();
        for (long stone : stones) {
            this.stones.put(stone, this.stones.getOrDefault(stone, 0L) + 1);
        }
    }

    public void blink() {
        HashMap<Long, Long> newStone = new HashMap<>();
        for (long stone : this.stones.keySet()) {
            if (stone == 0) {
                newStone.put(1L, newStone.getOrDefault(stone, 0L) + this.stones.get(stone));
            } else {
                if (this.evenDigits(stone)) {
                    ArrayList<Long> splits = this.splitNum(stone);
                    newStone.put(splits.get(0), newStone.getOrDefault(splits.get(0), 0L) + this.stones.get(stone));
                    newStone.put(splits.get(1), newStone.getOrDefault(splits.get(1), 0L) + this.stones.get(stone));
                } else {
                    newStone.put(stone * 2024, newStone.getOrDefault(stone * 2024, 0L) + this.stones.get(stone));
                }
            }
        }
        this.stones = newStone;
    }

    public static boolean evenDigits (long num) {
        String str = new StringBuilder(). append(num). toString();
        return (str.length() % 2 == 0);
    }

    private static ArrayList<Long> splitNum (long num) {
        String str = new StringBuilder(). append(num). toString();
        ArrayList<Long> res = new ArrayList<>();
        res.add(Long.valueOf(str.substring(0, str.length()/2)));
        res.add(Long.valueOf(str.substring(str.length()/2, str.length())));
        return res;
    }

    public long getNumberOfStones() {
        long res = 0;
        for (long stone : this.stones.keySet()) {
            res += this.stones.get(stone);
        }
        return res;
    }
}
