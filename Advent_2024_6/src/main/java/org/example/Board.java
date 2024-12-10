package org.example;

import java.util.ArrayList;


public class Board {
    private ArrayList<ArrayList<Field>> board;
    private Direction direction;
    private int xCoordinate;
    private int yCoordinate;

    public Board(ArrayList<ArrayList<Character>> boardCode) {
        this.initializeBoard(boardCode);
        this.direction = Direction.UP;
    }

    private void initializeBoard(ArrayList<ArrayList<Character>> boardCode) {
        this.board = new ArrayList<>();
        for (int i = 0; i < boardCode.size(); i ++) {
            ArrayList<Field> newLine = new ArrayList<>();
            for (int j = 0; j < boardCode.get(i).size(); j++) {
                char curr = boardCode.get(i).get(j);
                if (curr == '^') {
                    this.yCoordinate = i;
                    this.xCoordinate = j;
                    RegularField start = new RegularField();
                    start.markVisited();
                    newLine.add(start);
                }
                if (curr == '.') {
                    newLine.add(new RegularField());
                }
                if (curr == '#') {
                    newLine.add(new Obstacle());
                }
            }
            this.board.add(newLine);
        }
    }



    public boolean walk() {
        int status = this.step();
        while (status == 1) {
            status = this.step();
        }
        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int step() {
        // +1 for normal; -1 for escape; 0 for cycle
        int provisionalX = this.xCoordinate;
        int provisionalY = this.yCoordinate;
        if (this.direction == Direction.UP) {
            provisionalY -= 1;
        }
        if (this.direction == Direction.RIGHT) {
            provisionalX += 1;
        }
        if (this.direction == Direction.DOWN) {
            provisionalY += 1;
        }
        if (this.direction == Direction.LEFT) {
            provisionalX -= 1;
        }
        if ( provisionalX < 0 ||  provisionalX >= board.size() || provisionalY < 0 ||  provisionalY >= board.size() ) {
            Field currField = this.board.get(this.yCoordinate).get(this.xCoordinate);
            if (currField instanceof RegularField) {
                RegularField currFieldCast = (RegularField) currField;
                currFieldCast.markVisited();
            }

            return -1;
        } else {
            if (this.board.get(provisionalY).get(provisionalX).isObstacle()) {
                this.direction = this.rotate();
                return 1;
                //System.out.println("Obstacle");
                //System.out.println(this.direction);
                // System.out.println(this.printBoard());
            } else {
                Field currField = this.board.get(this.yCoordinate).get(this.xCoordinate);
                if (currField instanceof RegularField) {
                    RegularField currFieldCast = (RegularField) currField;
                    currFieldCast.markVisited();
                    if (currFieldCast.addDirection(this.direction)) {
                        //System.out.println("Cycle");
                        return 0;
                    }
                }
                this.setCoordinates(provisionalX, provisionalY);

                return 1;
            }
        }
    }

    public void setCoordinates(int provisionalX, int provisionalY) {
        this.xCoordinate = provisionalX;
        this.yCoordinate = provisionalY;
    }




    public Direction rotate() {
        if (this.direction.equals(Direction.UP)) {
            return Direction.RIGHT;
        }
        if (this.direction.equals(Direction.RIGHT)) {
            return Direction.DOWN;
        }
        if (this.direction.equals(Direction.DOWN)) {
            return Direction.LEFT;
        }
        return Direction.UP;

    }


    public String printBoard() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.size(); j++) {
                res.append(this.board.get(i).get(j).toString());
            }
            res.append("\n");
        }
        return res.toString();
    }

    public int calculateVisited() {
        int res = 0;
        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.size(); j++) {
                Field currField = this.board.get(i).get(j);
                if (currField instanceof RegularField) {
                    RegularField currFieldCast = (RegularField) currField;
                    if (currFieldCast.getVisited()) {
                        res += 1;
                    }
                }
            }
        }
        return res;
    }

    public boolean setTrap(int i, int j) {
        if (!(this.board.get(i).get(j).isObstacle()) && !((i == this.yCoordinate) && (j == this.xCoordinate))) {
            this.board.get(i).set(j, new Obstacle());
            //System.out.println(printBoard());
            return true;
        }
        return true;
    }


}
