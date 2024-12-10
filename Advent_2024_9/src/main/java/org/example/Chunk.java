package org.example;

public class Chunk {
    private int position;
    private int length;

    public Chunk(int position, int length) {
        this.position = position;
        this.length = length;
    }

    public int getPosition() {
        return this.position;
    }

    public int getLength() {
        return this.length;
    }

    public void move(int newPosition) {
        this.position = newPosition;
    }

    public void changeSize (int newSize) {
        this.length = newSize;
    }

    @Override
    public String toString() {
        return "Start: " + this.position + "; Length: " + this.length + "\n";
    }
}
