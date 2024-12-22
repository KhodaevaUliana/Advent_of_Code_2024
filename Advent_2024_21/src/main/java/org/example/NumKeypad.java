package org.example;

import java.util.*;

public class NumKeypad {
    private HashMap<Pair, ArrayList<String>> ways;
    private ArrayList<Character> syms;

    public NumKeypad() {
        Character[] arr = {'A', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        this.syms = new ArrayList<>(Arrays.asList(arr));
        this.ways = new HashMap<>();
        for (char sym1 : this.syms) {
            for (char sym2 : this.syms) {
                Pair curr = new Pair(sym1, sym2);
                this.ways.put(curr, new ArrayList<>());
                if (sym1 == sym2) {
                    this.ways.get(curr).add("A");
                }
                if (sym1 == 'A' && sym2 == '0') {
                    this.ways.get(curr).add("<A");
                }
                if (sym1 == '0' && sym2 == 'A') {
                    this.ways.get(curr).add(">A");
                }
                if ((sym1 - sym2) == 1 && sym1 != '4' && sym1 != '7' && sym1 != '1') {
                    this.ways.get(curr).add("<A");
                }
                if ((sym1 - sym2) == -1 && sym2 != '4' && sym2 != '7' && sym2 != '1') {
                    this.ways.get(curr).add(">A");
                }
                if (curr.equals(new Pair('9', '7')) || curr.equals(new Pair('6', '4')) || curr.equals(new Pair('3', '1'))) {
                    this.ways.get(curr).add("<<A");
                }
                if (curr.equals(new Pair('7', '9')) || curr.equals(new Pair('4', '6')) || curr.equals(new Pair('1', '3'))) {
                    this.ways.get(curr).add(">>A");
                }

                if ((sym2 - sym1) == 3 && sym1 != '0') {
                    this.ways.get(curr).add("^A");
                }
                if ((sym1 - sym2) == 3 && sym2 != '0') {
                    this.ways.get(curr).add("vA");
                }
                if (curr.equals(new Pair('0', '2')) || curr.equals(new Pair('A', '3'))) {
                    this.ways.get(curr).add("^A");
                }
                if (curr.equals(new Pair('2', '0')) || curr.equals(new Pair('3', 'A'))) {
                    this.ways.get(curr).add("vA");
                }
                if (sym2 - sym1 == 6 && sym2 != '6') {
                    this.ways.get(curr).add("^^A");
                }
                if (sym1 - sym2 == 6 && sym1 != '6') {
                    this.ways.get(curr).add("vvA");
                }
                if (curr.equals(new Pair('0', '5')) || curr.equals(new Pair('A', '6'))) {
                    this.ways.get(curr).add("^^A");
                }
                if (curr.equals(new Pair('5', '0')) || curr.equals(new Pair('6', 'A'))) {
                    this.ways.get(curr).add("vvA");
                }
                if (curr.equals(new Pair('0', '8')) || curr.equals(new Pair('A', '9'))) {
                    this.ways.get(curr).add("^^^A");
                }
                if (curr.equals(new Pair('8', '0')) || curr.equals(new Pair('9', 'A'))) {
                    this.ways.get(curr).add("vvvA");
                }
                if (curr.equals(new Pair('0', '1'))) {
                    this.ways.get(curr).add("^<A");
                }
                if (curr.equals(new Pair('1', '0'))) {
                    this.ways.get(curr).add(">vA");
                }
                if (curr.equals(new Pair('A', '2')) || curr.equals(new Pair('2', '4')) ||
                        curr.equals(new Pair('3', '5'))  || curr.equals(new Pair('5', '7')) ||
                        curr.equals(new Pair('6', '8'))) {
                    this.ways.get(curr).add("^<A");
                    this.ways.get(curr).add("<^A");
                }
                if (curr.equals(new Pair('2', 'A')) || curr.equals(new Pair('4', '2')) ||
                        curr.equals(new Pair('5', '3'))  || curr.equals(new Pair('7', '5')) ||
                        curr.equals(new Pair('8', '6'))) {
                    this.ways.get(curr).add("v>A");
                    this.ways.get(curr).add(">vA");
                }
                if (curr.equals(new Pair('0', '4'))) {
                    this.ways.get(curr).add("^^<A");
                    this.ways.get(curr).add("^<^A");
                }
                if (curr.equals(new Pair('4', '0'))) {
                    this.ways.get(curr).add(">vvA");
                    this.ways.get(curr).add("v>vA");
                }
                if (curr.equals(new Pair('A', '5')) || curr.equals(new Pair('2', '7'))
                        || curr.equals(new Pair('3', '8'))) {
                    this.ways.get(curr).add("^^<A");
                    this.ways.get(curr).add("^<^A");
                    this.ways.get(curr).add("<^^A");
                }
                if (curr.equals(new Pair('5', 'A')) || curr.equals(new Pair('7', '2'))
                        || curr.equals(new Pair('8', '3'))) {
                    this.ways.get(curr).add(">vvA");
                    this.ways.get(curr).add("v>vA");
                    this.ways.get(curr).add("vv>A");
                }
                if (curr.equals(new Pair('0', '7'))) {
                    this.ways.get(curr).add("^^^<A");
                    this.ways.get(curr).add("^^<^A");
                    this.ways.get(curr).add("^<^^A");
                }
                if (curr.equals(new Pair('7', '0'))) {
                    this.ways.get(curr).add(">vvvA");
                    this.ways.get(curr).add("v>vvA");
                    this.ways.get(curr).add("vv>vA");
                }
                if (curr.equals(new Pair('A', '1'))) {
                    this.ways.get(curr).add("^<<A");
                    this.ways.get(curr).add("<^<A");
                }
                if (curr.equals(new Pair('1', 'A'))) {
                    this.ways.get(curr).add(">>vA");
                    this.ways.get(curr).add(">v>A");
                }
                if (curr.equals(new Pair('3', '4')) || curr.equals(new Pair('6', '7'))) {
                    this.ways.get(curr).add("^<<A");
                    this.ways.get(curr).add("<^<A");
                    this.ways.get(curr).add("<<^A");
                }
                if (curr.equals(new Pair('4', '3')) || curr.equals(new Pair('7', '6'))) {
                    this.ways.get(curr).add(">>vA");
                    this.ways.get(curr).add(">v>A");
                    this.ways.get(curr).add("v>>A");
                }
                if (curr.equals(new Pair('3', '7'))) {
                    this.ways.get(curr).add("^^<<A");
                    this.ways.get(curr).add("^<^<A");
                    this.ways.get(curr).add("^<<^A");
                    this.ways.get(curr).add("<^^<A");
                    this.ways.get(curr).add("<^<^A");
                    this.ways.get(curr).add("<<^^A");
                }
                if (curr.equals(new Pair('7', '3'))) {
                    this.ways.get(curr).add("vv>>A");
                    this.ways.get(curr).add("v>v>A");
                    this.ways.get(curr).add("v>>vA");
                    this.ways.get(curr).add(">vv>A");
                    this.ways.get(curr).add(">v>vA");
                    this.ways.get(curr).add(">>vvA");
                }
                if (curr.equals(new Pair('A', '4'))) {
                    this.ways.get(curr).add("^^<<A");
                    this.ways.get(curr).add("^<^<A");
                    this.ways.get(curr).add("^<<^A");
                    this.ways.get(curr).add("<^^<A");
                    this.ways.get(curr).add("<^<^A");
                }
                if (curr.equals(new Pair('4', 'A'))) {
                    this.ways.get(curr).add(">>vvA");
                    this.ways.get(curr).add("v>v>A");
                    this.ways.get(curr).add("v>>vA");
                    this.ways.get(curr).add(">vv>A");
                    this.ways.get(curr).add(">v>vA");
                }
                if (curr.equals(new Pair('3', '7'))) {
                    this.ways.get(curr).add("^^<<A");
                    this.ways.get(curr).add("^<^<A");
                    this.ways.get(curr).add("^<<^A");
                    this.ways.get(curr).add("<^^<A");
                    this.ways.get(curr).add("<^<^A");
                    this.ways.get(curr).add("<<^^A");
                }
                if (curr.equals(new Pair('7', '3'))) {
                    this.ways.get(curr).add("vv>>A");
                    this.ways.get(curr).add("v>v>A");
                    this.ways.get(curr).add("v>>vA");
                    this.ways.get(curr).add(">vv>A");
                    this.ways.get(curr).add(">v>vA");
                    this.ways.get(curr).add(">>vvA");
                }
                if (curr.equals(new Pair('A', '7'))) {
                    //from 3
                    this.ways.get(curr).add("^^^<<A");
                    this.ways.get(curr).add("^^<^<A");
                    this.ways.get(curr).add("^^<<^A");
                    this.ways.get(curr).add("^<^^<A");
                    this.ways.get(curr).add("^<^<^A");
                    this.ways.get(curr).add("^<<^^A");
                    //from A02
                    this.ways.get(curr).add("<^^^<A");
                    this.ways.get(curr).add("<^^<^A");
                    this.ways.get(curr).add("<^<^^A");
                }
                if (curr.equals(new Pair('7', 'A'))) {
                    //..3A
                    this.ways.get(curr).add(">>vvvA");
                    this.ways.get(curr).add("vv>>vA");
                    this.ways.get(curr).add("v>v>vA");
                    this.ways.get(curr).add("v>>vvA");
                    this.ways.get(curr).add(">vv>vA");
                    this.ways.get(curr).add(">v>vvA");
                    //..20A
                    this.ways.get(curr).add(">vvv>A");
                    this.ways.get(curr).add("v>vv>A");
                    this.ways.get(curr).add("vv>v>A");
                }
                if (curr.equals(new Pair('0', '6')) || curr.equals(new Pair('1', '8')) || curr.equals(new Pair('2', '9'))) {
                    this.ways.get(curr).add("^^>A");
                    this.ways.get(curr).add("^>^A");
                    this.ways.get(curr).add(">^^A");
                }
                if (curr.equals(new Pair('6', '0')) || curr.equals(new Pair('8', '1')) || curr.equals(new Pair('9', '2'))) {
                    this.ways.get(curr).add("vv<A");
                    this.ways.get(curr).add("v<vA");
                    this.ways.get(curr).add("<vvA");
                }
                if (curr.equals(new Pair('0', '9'))) {
                    this.ways.get(curr).add(">^^^A");
                    this.ways.get(curr).add("^^^>A");
                    this.ways.get(curr).add("^^>^A");
                    this.ways.get(curr).add("^>^^A");
                }
                if (curr.equals(new Pair('9', '0'))) {
                    this.ways.get(curr).add("<vvvA");
                    this.ways.get(curr).add("vvv<A");
                    this.ways.get(curr).add("vv<vA");
                    this.ways.get(curr).add("v<vvA");
                }
                if (curr.equals(new Pair('1', '9'))) {
                    this.ways.get(curr).add("^^>>A");
                    this.ways.get(curr).add("^>^>A");
                    this.ways.get(curr).add("^>>^A");
                    this.ways.get(curr).add(">^^>A");
                    this.ways.get(curr).add(">^>^A");
                    this.ways.get(curr).add(">>^^A");
                }
                if (curr.equals(new Pair('9', '1'))) {
                    this.ways.get(curr).add("vv<<A");
                    this.ways.get(curr).add("v<v<A");
                    this.ways.get(curr).add("v<<vA");
                    this.ways.get(curr).add("<vv<A");
                    this.ways.get(curr).add("<v<vA");
                    this.ways.get(curr).add("<<vvA");
                }
                if (curr.equals(new Pair('A','8'))) {
                    this.ways.get(curr).add("^^^<A");
                    this.ways.get(curr).add("^^<^A");
                    this.ways.get(curr).add("^<^^A");
                    this.ways.get(curr).add("<^^^A");
                }
                if (curr.equals(new Pair('8','A'))) {
                    this.ways.get(curr).add("vvv>A");
                    this.ways.get(curr).add("vv>vA");
                    this.ways.get(curr).add("v>vvA");
                    this.ways.get(curr).add(">vvvA");
                }
                if (sym2 - sym1 == 4 && sym2 != '7' && sym2 != '4') {
                    this.ways.get(curr).add("^>A");
                    this.ways.get(curr).add(">^A");
                }
                if (sym1 - sym2 == 4 && sym1 != '7' && sym1 != '4') {
                    this.ways.get(curr).add("v<A");
                    this.ways.get(curr).add("<vA");
                }
                if (curr.equals(new Pair('0', '3'))) {
                    this.ways.get(curr).add("^>A");
                    this.ways.get(curr).add(">^A");
                }
                if (curr.equals(new Pair('3', '0'))) {
                    this.ways.get(curr).add("v<A");
                    this.ways.get(curr).add("<vA");
                }
                if (curr.equals(new Pair('1', '6')) || curr.equals(new Pair('4', '9'))) {
                    this.ways.get(curr).add("^>>A");
                    this.ways.get(curr).add(">^>A");
                    this.ways.get(curr).add(">>^A");
                }
                if (curr.equals(new Pair('6', '1')) || curr.equals(new Pair('9', '4'))) {
                    this.ways.get(curr).add("v<<A");
                    this.ways.get(curr).add("<v<A");
                    this.ways.get(curr).add("<<vA");
                }
                if (this.ways.get(curr).isEmpty()) {
                    System.out.println("Problem! " + curr);
                }
            }
        }
        //System.out.println(this.ways);

    }

    public ArrayList<String> getInstructionToCode(String code) {
        ArrayList<String> res = new ArrayList<>();
        res.addAll(this.ways.get(new Pair('A', code.charAt(0))));
        for (int i = 0; i < code.length() - 1; i++) {
            ArrayList<String> currArr = new ArrayList<>();
            Pair curr = new Pair(code.charAt(i), code.charAt(i+1));
            for (String prev : res) {
                for (String option : this.ways.get(curr)) {
                    currArr.add(prev + option);
                }
            }
            res = currArr;
        }
        //ensure correctness
        if (res.isEmpty()) {
            System.out.println("Error! No ways");
        } else {
            int length = res.get(0).length();
            for (String opt : res) {
                if (opt.length() != length) {
                    System.out.println("Error! Different length");
                }
            }
        }

        return res;
    }

}
