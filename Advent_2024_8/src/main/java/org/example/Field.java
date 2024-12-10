package org.example;

public class Field {
    private boolean isAntenna;
    private boolean isAntiNode;
    private char frequency;

    public Field (char c) {
        if (c != '.' && c != '#') {
            this.isAntenna = true;
            this.frequency = c;
        } else {
            this.isAntenna = false;
        }
        this.isAntiNode = false;
    }

    public boolean isAntenna() {
        return this.isAntenna;
    }

    public boolean isAntiNode() {
        return this.isAntiNode;
    }



    public void setAntiNode() {
        this.isAntiNode = true;
    }

    @Override
    public String toString() {
        if (!(this.isAntenna)) {
            if (this.isAntiNode) {
                return "#";
            } else {
                return ".";
            }
        } else {
            return String.valueOf(this.frequency);
        }
    }

}
