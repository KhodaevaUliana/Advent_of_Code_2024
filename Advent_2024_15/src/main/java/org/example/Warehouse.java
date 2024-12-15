package org.example;

import java.util.ArrayList;

public class Warehouse {
    private ArrayList<ArrayList<Field>> fields;
    private int robotFirstCoordinate;
    private int robotSecondCoordinate;
    private String robotActions;

    public Warehouse (ArrayList<ArrayList<Character>> boardCode, String robotActions) {
        this.robotActions = robotActions;
        this.fields = new ArrayList<>();
        for (int i = 0; i < boardCode.size(); i++) {
            ArrayList<Field> newLine = new ArrayList<>();
            for (int j = 0; j < boardCode.get(i).size(); j++) {
                char curr = boardCode.get(i).get(j);
                switch (curr) {
                    case '@':
                        this.robotFirstCoordinate = i;
                        this.robotSecondCoordinate = j;
                        newLine.add(new Space(false));
                        break;
                    case 'O':
                        newLine.add(new Space(true));
                        break;
                    case '.':
                        newLine.add(new Space(false));
                        break;
                    case '#':
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
        }
        //try to find a free space
        currFirst -= 1;
        while (currFirst >= 0 && this.fields.get(currFirst).get(this.robotSecondCoordinate) instanceof Space) {
            spaceInQuestion = (Space) this.fields.get(currFirst).get(this.robotSecondCoordinate);
            if (spaceInQuestion.withBox()) {
                currFirst -= 1;
            } else {
                //a free space is found!!!
                spaceInQuestion.putBox();
                //remove the nearest to the robot box
                Space nearest = (Space) this.fields.get(this.robotFirstCoordinate - 1).get(this.robotSecondCoordinate);
                nearest.removeBox();
                this.robotFirstCoordinate -= 1;
                return;
            }
        }
        return;
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
        }
        //try to find a free space
        currFirst += 1;
        while (currFirst < this.fields.size() && this.fields.get(currFirst).get(this.robotSecondCoordinate) instanceof Space) {
            spaceInQuestion = (Space) this.fields.get(currFirst).get(this.robotSecondCoordinate);
            if (spaceInQuestion.withBox()) {
                currFirst += 1;
            } else {
                //a free space is found!!!
                spaceInQuestion.putBox();
                //remove the nearest to the robot box
                Space nearest = (Space) this.fields.get(this.robotFirstCoordinate + 1).get(this.robotSecondCoordinate);
                nearest.removeBox();
                this.robotFirstCoordinate += 1;
                return;
            }
        }
        return;
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
        }
        //try to find a free space
        currSecond -= 1;
        while (currSecond >= 0 && this.fields.get(this.robotFirstCoordinate).get(currSecond) instanceof Space) {
            spaceInQuestion = (Space) this.fields.get(this.robotFirstCoordinate).get(currSecond);
            if (spaceInQuestion.withBox()) {
                currSecond -= 1;
            } else {
                //a free space is found!!!
                spaceInQuestion.putBox();
                //remove the nearest to the robot box
                Space nearest = (Space) this.fields.get(this.robotFirstCoordinate).get(this.robotSecondCoordinate - 1);
                nearest.removeBox();
                this.robotSecondCoordinate -= 1;
                return;
            }
        }
        return;
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
        }
        //try to find a free space
        currSecond += 1;
        while (currSecond < this.fields.get(0).size() && this.fields.get(this.robotFirstCoordinate).get(currSecond) instanceof Space) {
            spaceInQuestion = (Space) this.fields.get(this.robotFirstCoordinate).get(currSecond);
            if (spaceInQuestion.withBox()) {
                currSecond += 1;
            } else {
                //a free space is found!!!
                spaceInQuestion.putBox();
                //remove the nearest to the robot box
                Space nearest = (Space) this.fields.get(this.robotFirstCoordinate).get(this.robotSecondCoordinate + 1);
                nearest.removeBox();
                this.robotSecondCoordinate += 1;
                return;
            }
        }
        return;
    }

    public int getSumOfGPSCoordinate() {
        int res = 0;
        for (int i = 0; i < this.fields.size(); i++) {
            for (int j = 0; j < this.fields.get(i).size(); j++) {
                Field curr = this.fields.get(i).get(j);
                if (curr instanceof Space) {
                    Space currSpace = (Space) curr;
                    if (currSpace.withBox()) {
                        res += (100 * i + j);
                    }
                }
            }
        }
        return res;
    }


}
