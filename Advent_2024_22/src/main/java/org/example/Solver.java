package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Solver {
    private ArrayList<Long> input;
    private ArrayList<ArrayList<Integer>> diffs;
    private ArrayList<ArrayList<Integer>> lastDigits;
    private HashMap<Sequence, Integer> sequencePrice;

    public Solver (ArrayList<Long> input) {
        this.input = input;
        this.diffs = new ArrayList<>();
        this.lastDigits = new ArrayList<>();
        this.sequencePrice = new HashMap<>();
        this.generateDiffs();
    }

    public void generateDiffs() {
        SecretGenerator secgen = new SecretGenerator();
        for (long currSecret : input) {
            int prevLastDigit = (int) currSecret % 10;
            ArrayList<Integer> currDiffs = new ArrayList<>();
            ArrayList<Integer> currLastDigits = new ArrayList<>();
            for (int j = 0; j < 2000; j++) {
                currSecret = secgen.generateNewSecret(currSecret);
                int currLastDigit = (int) currSecret % 10;
                currLastDigits.add(currLastDigit);
                currDiffs.add(currLastDigit - prevLastDigit);
                prevLastDigit = currLastDigit;
            }
            this.diffs.add(currDiffs);
            this.lastDigits.add(currLastDigits);
        }
    }

    public void initializeSequencePrice() {
        for (int i = 0; i < this.diffs.size(); i++) {
            HashMap<Sequence, Integer> seqValCurr = new HashMap<>();
            ArrayList<Integer> currSeqArr = new ArrayList<>();
            currSeqArr.add(this.diffs.get(i).get(0));
            currSeqArr.add(this.diffs.get(i).get(1));
            currSeqArr.add(this.diffs.get(i).get(2));
            currSeqArr.add(this.diffs.get(i).get(3));
            Sequence currSeq = new Sequence(currSeqArr);
            seqValCurr.put(currSeq, this.lastDigits.get(i).get(3));
            for (int j = 4; j < this.diffs.get(i).size(); j++) {
                currSeqArr.remove(0);
                currSeqArr.add(this.diffs.get(i).get(j));
                currSeq = new Sequence(currSeqArr);
                if (!seqValCurr.containsKey(currSeq)) {
                    seqValCurr.put(currSeq, this.lastDigits.get(i).get(j));
                }
            }
            for (Sequence seq : seqValCurr.keySet()) {
                if (this.sequencePrice.containsKey(seq)) {
                    int currVal = this.sequencePrice.get(seq);
                    this.sequencePrice.put(seq, currVal + seqValCurr.get(seq));
                } else {
                    this.sequencePrice.put(seq, seqValCurr.get(seq));
                }
            }

        }

    }

    public int findNumOfBananas() {
        int sum = 0;
        for (Sequence seq : this.sequencePrice.keySet()) {
            int curr = this.sequencePrice.get(seq);
            if (curr > sum) {
                sum = curr;
            }
        }
        return sum;
    }
}
