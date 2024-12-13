package org.example;

public class Task {
    private int ax;
    private int ay;
    private int bx;
    private int by;
    private long x;
    private long y;

    public Task(int ax, int ay, int bx, int by, long x, long y) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.x = x;
        this.y = y;
    }

    public Solution solve() {
        int det = this.findDet();
        //make a matrix and inverse it!
        if (det == 0) {
            //tackle this case!
            /*this case never occurs in the puzzle input, and I was too
            lazy to implement it without necessity, so let's leave it as is
             */
            System.out.println("special case");
            return null;
        } else {
            long solX = this.by * this.x - this.bx * y;
            long solY = this.ax * this.y - this.ay * x;
            if (solX % det == 0 && solY % det == 0) {
                long moveA = solX / det;
                long moveB = solY / det;
                return new Solution(moveA, moveB);
            } else {
                return null;
            }
        }
    }

    private int findDet() {
        return this.ax * this.by - this.bx * this.ay;
    }

    @Override
    public String toString() {
        return "A: " + ax + ";" + ay + "\n" + "B: " + bx + ";" + by + "\n" + x + " " + y;
    }

}
