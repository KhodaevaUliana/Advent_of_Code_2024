package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DirectionalKeypad {
    private HashMap<Pair, ArrayList<String>> ways;
    private ArrayList<Character> syms;

    public DirectionalKeypad() {
        this.ways = new HashMap<>();
        Character[] arr = {'A', 'v', '^', '<', '>'};
        this.syms = new ArrayList<>(Arrays.asList(arr));
        for (char sym1 : this.syms) {
            for (char sym2 : this.syms) {
                Pair curr = new Pair(sym1, sym2);
                this.ways.put(curr, new ArrayList<>());
                if (sym1 == sym2) {
                    this.ways.get(curr).add("A");
                }
                if (curr.equals(new Pair('A', '^'))) {
                    this.ways.get(curr).add("<A");
                }
                if (curr.equals(new Pair('A', '>'))) {
                    this.ways.get(curr).add("vA");
                }
                if (curr.equals(new Pair('A', 'v'))) {
                    this.ways.get(curr).add("<vA");
                    this.ways.get(curr).add("v<A");
                    //this.ways.get(curr).add("<vA"); //was first
                }
                if (curr.equals(new Pair('A', '<'))) {
                    this.ways.get(curr).add("v<<A");
                    this.ways.get(curr).add("<v<A");
                }
                if (curr.equals(new Pair('^', 'A'))) {
                    this.ways.get(curr).add(">A");
                }
                if (curr.equals(new Pair('^', 'A'))) {
                    this.ways.get(curr).add(">A");
                }
                if (curr.equals(new Pair('^', '>'))) {
                    this.ways.get(curr).add("v>A");
                    this.ways.get(curr).add(">vA");
                    //this.ways.get(curr).add("v>A");
                }
                if (curr.equals(new Pair('^', 'v'))) {
                    this.ways.get(curr).add("vA");
                }
                if (curr.equals(new Pair('^', '<'))) {
                    this.ways.get(curr).add("v<A");
                }
                if (curr.equals(new Pair('>', 'A'))) {
                    this.ways.get(curr).add("^A");
                }
                if (curr.equals(new Pair('>', 'v'))) {
                    this.ways.get(curr).add("<A");
                }
                if (curr.equals(new Pair('>', '<'))) {
                    this.ways.get(curr).add("<<A");
                }
                if (curr.equals(new Pair('>', '^'))) {
                    this.ways.get(curr).add("<^A");
                    this.ways.get(curr).add("^<A");
                    //this.ways.get(curr).add("<^A"); //WAS FIRST
                }
                if (curr.equals(new Pair('v', 'A'))) {
                    this.ways.get(curr).add("^>A");
                    this.ways.get(curr).add(">^A");
                    //this.ways.get(curr).add("^>A"); //WAS FIRST!
                }
                if (curr.equals(new Pair('v', '>'))) {
                    this.ways.get(curr).add(">A");
                }
                if (curr.equals(new Pair('v', '<'))) {
                    this.ways.get(curr).add("<A");
                }
                if (curr.equals(new Pair('v', '^'))) {
                    this.ways.get(curr).add("^A");
                }
                if (curr.equals(new Pair('<', 'A'))) {
                    this.ways.get(curr).add(">>^A");
                    this.ways.get(curr).add(">^>A");
                }
                if (curr.equals(new Pair('<', '>'))) {
                    this.ways.get(curr).add(">>A");
                }
                if (curr.equals(new Pair('<', 'v'))) {
                    this.ways.get(curr).add(">A");
                }
                if (curr.equals(new Pair('<', '^'))) {
                    this.ways.get(curr).add(">^A");
                }
                if (this.ways.get(curr).isEmpty()) {
                    System.out.println("Problem! " + curr);
                }
            }
        }


    }


    public String getOneDirectionToInstruction(String code) {
        StringBuilder res = new StringBuilder();
        code = "A" + code;
        for (int i = 0; i < code.length() - 1; i++) {
            Pair curr = new Pair(code.charAt(i), code.charAt(i+1));
            res.append(this.ways.get(curr).get(0));
        }
        return res.toString();
    }
}
