package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Sequence {
    private int num1;
    private int num2;
    private int num3;
    private int num4;

    public Sequence(ArrayList<Integer> sequence) {
        this.num1 = sequence.get(0);
        this.num2 = sequence.get(1);
        this.num3 = sequence.get(2);
        this.num4 = sequence.get(3);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sequence sequence)) return false;
        return num1 == sequence.num1 && num2 == sequence.num2 && num3 == sequence.num3 && num4 == sequence.num4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num1, num2, num3, num4);
    }

    @Override
    public String toString() {
        return this.num1 + " " + this.num2 + " " +  this.num2 + " " +  this.num3;
    }
}
