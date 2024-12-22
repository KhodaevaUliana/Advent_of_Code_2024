package org.example;

import java.util.Objects;

public class Pair {
    private char sym1;
    private char sym2;

    public Pair(char sym1, char sym2) {
        this.sym1 = sym1;
        this.sym2 = sym2;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair pair)) return false;
        return sym1 == pair.sym1 && sym2 == pair.sym2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.sym1);
        res.append(";");
        res.append(this.sym2);
        return res.toString();
    }


    @Override
    public int hashCode() {
        return Objects.hash(sym1, sym2);
    }
}
