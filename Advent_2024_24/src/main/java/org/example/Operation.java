package org.example;

import java.util.HashMap;
import java.util.Objects;

public class Operation {
    private String firstArg;
    private String operator;
    private String secondArg;
    private String result;

    public Operation(String firstArg, String operator, String secondArg, String result) {
        this.firstArg = firstArg;
        this.operator = operator;
        this.secondArg = secondArg;
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public String getFirstArg() {
        return this.firstArg;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getSecondArg() {
        return this.secondArg;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Operation operation)) return false;
        return ((Objects.equals(firstArg, operation.firstArg) && Objects.equals(operator, operation.operator) && Objects.equals(secondArg, operation.secondArg))
                || (Objects.equals(secondArg, operation.firstArg) && Objects.equals(operator, operation.operator) && Objects.equals(firstArg, operation.secondArg)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstArg, operator, secondArg);
    }

    public boolean execute(HashMap<String, Boolean> data) {
        if (data.containsKey(firstArg) && data.containsKey(secondArg)) {
            switch (this.operator) {
                case "AND":
                    data.put(result, data.get(firstArg) && data.get(secondArg));
                    return true;
                case "OR":
                    data.put(result, data.get(firstArg) || data.get(secondArg));
                    return true;
                case "XOR":
                    data.put(result, data.get(firstArg) ^ data.get(secondArg));
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.firstArg + " " + this.operator + " " + this.secondArg + " " + this.result;
    }
}
