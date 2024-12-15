package org.example;

import java.util.ArrayList;

public class WarehouseExtended {
    private ArrayList<ArrayList<Field>> fields;
    private int robotFirstCoordinate;
    private int robotSecondCoordinate;
    private String robotActions;
    private ArrayList<Box> boxes;

    public WarehouseExtended (ArrayList<ArrayList<Character>> boardCode, String robotActions) {
        this.robotActions = robotActions;
        this.boxes = new ArrayList<>();
        this.fields = new ArrayList<>();
        for (int i = 0; i < boardCode.size(); i++) {
            ArrayList<Field> newLine = new ArrayList<>();
            for (int j = 0; j < boardCode.get(i).size(); j++) {
                char curr = boardCode.get(i).get(j);
                switch (curr) {
                    case '@':
                        this.robotFirstCoordinate = i;
                        this.robotSecondCoordinate = 2 * j;
                        newLine.add(new Space(false));
                        newLine.add(new Space(false));
                        break;
                    case 'O':
                        Box newBox = new Box(i, 2*j, this);
                        newLine.add(new Space(true, newBox));
                        newLine.add(new Space(true, newBox));
                        this.boxes.add(newBox);
                        break;
                    case '.':
                        newLine.add(new Space(false));
                        newLine.add(new Space(false));
                        break;
                    case '#':
                        newLine.add(new Obstacle());
                        newLine.add(new Obstacle());
                        break;
                    default:
                        System.out.println("Unknown character");
                        System.out.println(curr);
                }

            }
            this.fields.add(newLine);
        }
    }

    public void moveLeft() {
        if (this.robotSecondCoordinate == 0) {
            return;
        }
        int currSecond = this.robotSecondCoordinate - 1;
        Field toBeChecked = this.fields.get(this.robotFirstCoordinate).get(currSecond);
        if (toBeChecked instanceof Obstacle) {
            return;
        }
        Space spaceInQuestion = (Space) toBeChecked;
        if (!spaceInQuestion.withBox()) {
            this.robotSecondCoordinate -= 1;
            return;
        } else {
            if (spaceInQuestion.getBox().moveableLeft()) {
                spaceInQuestion.getBox().moveLeft();
                this.robotSecondCoordinate -= 1;
                return;
            } else {
                //System.out.println("Box is not moveable");
            }
        }

    }

    public void moveRight() {
        if (this.robotSecondCoordinate == (this.fields.get(0).size() - 1)) {
            return;
        }
        int currSecond = this.robotSecondCoordinate + 1;
        Field toBeChecked = this.fields.get(this.robotFirstCoordinate).get(currSecond);
        if (toBeChecked instanceof Obstacle) {
            return;
        }
        Space spaceInQuestion = (Space) toBeChecked;
        if (!spaceInQuestion.withBox()) {
            this.robotSecondCoordinate += 1;
            return;
        } else {
            if (spaceInQuestion.getBox().moveableRight()) {
                spaceInQuestion.getBox().moveRight();
                this.robotSecondCoordinate += 1;
                return;
            } else {
                //System.out.println("Box is not moveable");
            }
        }

    }

    public void moveUp() {
        if (this.robotFirstCoordinate == 0) {
            return;
        }
        int currFirst = this.robotFirstCoordinate - 1;
        Field toBeChecked = this.fields.get(currFirst).get(this.robotSecondCoordinate);
        if (toBeChecked instanceof Obstacle) {
            return;
        }
        Space spaceInQuestion = (Space) toBeChecked;
        if (!spaceInQuestion.withBox()) {
            this.robotFirstCoordinate -= 1;
            return;
        } else {
            if (spaceInQuestion.getBox().moveableUp()) {
                spaceInQuestion.getBox().moveUp();
                this.robotFirstCoordinate -= 1;
                return;
            } else {
                //System.out.println("Box is not moveable");
            }
        }

    }

    public void moveDown() {
        if (this.robotFirstCoordinate == (this.fields.size() - 1)) {
            return;
        }
        int currFirst = this.robotFirstCoordinate + 1;
        Field toBeChecked = this.fields.get(currFirst).get(this.robotSecondCoordinate);
        if (toBeChecked instanceof Obstacle) {
            return;
        }
        Space spaceInQuestion = (Space) toBeChecked;
        if (!spaceInQuestion.withBox()) {
            this.robotFirstCoordinate += 1;
            return;
        } else {
            if (spaceInQuestion.getBox().moveableDown()) {
                spaceInQuestion.getBox().moveDown();
                this.robotFirstCoordinate += 1;
                return;
            } else {
                //System.out.println("Box is not moveable");
            }
        }

    }

    public void execute() {
        for (int i = 0; i < this.robotActions.length(); i++) {
            switch (this.robotActions.charAt(i)) {
                case '>':
                    this.moveRight();
                    break;
                case '<':
                    this.moveLeft();
                    break;
                case '^':
                    this.moveUp();
                    break;
                case 'v':
                    this.moveDown();
                    break;
                default:
                    System.out.println("Unknown character " + this.robotActions.charAt(i));
            }
            //System.out.println(this.toString());
        }
    }


    public ArrayList<ArrayList<Field>> getFields(){
        return this.fields;
    }

    public int getSumOfGPSCoordinates() {
        int res = 0;
        for (Box box : this.boxes) {
            res += box.getCoordinates().get(0) * 100 + box.getCoordinates().get(1);
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <  this.fields.size(); i++) {
            for (int j = 0; j < this.fields.get(i).size(); j++) {
                if (i == this.robotFirstCoordinate && j == this.robotSecondCoordinate) {
                    res.append("@");
                } else {
                    res.append(this.fields.get(i).get(j).toString());
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
