package org.example;

public class Space extends Field {
    private boolean hasABox;
    private Box box;

    public Space (boolean hasABox) {
        this.hasABox = hasABox;
    }

    public Space (boolean hasABox, Box box) {
        this.hasABox = true;
        this.box = box;
    }


    public void removeBox() {
        this.hasABox = false;
        this.box = null;
    }

    public boolean withBox() {
        return this.hasABox;
    }

    public void putBox() {
        this.hasABox = true;
    }

    public Box getBox() {
        return this.box;
    }

    public void putBox(Box box) {
        this.hasABox = true;
        this.box = box;
    }

    @Override
    public String toString() {
        if (this.box == null) {
            if (this.hasABox) {
                return "0";
            } else {
                return ".";
            }
        }
        if (this.hasABox) {
            return this.box.toString();
        } else {
            return ".";
        }


    }
}
