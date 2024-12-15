package org.example;
import java.util.ArrayList;

public class Box {
    private WarehouseExtended warehouse;
    private ArrayList<Integer> coordinates; //of the left part
    private boolean isFirstPartPrinted;

    public Box (int firstCoordinate, int secondCoordinate, WarehouseExtended warehouse) {
        this.warehouse = warehouse;
        this.coordinates = new ArrayList<>();
        this.coordinates.add(firstCoordinate);
        this.coordinates.add(secondCoordinate);
        this.isFirstPartPrinted = false;
    }

    public ArrayList<Integer> getCoordinates() {
        return this.coordinates;
    }

    public boolean moveableLeft() {
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        if (currSecond == 0) {
            return false;
        }
        currSecond--;
        if (this.warehouse.getFields().get(currFirst).get(currSecond) instanceof Obstacle) {
            //System.out.println("obstacle at " + currFirst + ";"+ currSecond);
            return false;
        } else {
            Space currSpace = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
            if (currSpace.withBox()) {
                //System.out.println("Next Box at " + currSpace.getBox().getCoordinates());
                return currSpace.getBox().moveableLeft();
            } else {
                return true;
            }
        }
    }
    public void moveLeft() {
        if (!this.moveableLeft()) {
            return;
        }
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        Space spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond - 1);
        if (spaceInQuestion.withBox()) {
            spaceInQuestion.getBox().moveLeft();
        }
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 1);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond - 1);
        spaceInQuestion.putBox(this);
        this.coordinates.set(1, currSecond - 1);

    }
    public boolean moveableRight() {
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        if (currSecond == (this.warehouse.getFields().get(0).size() - 2)) {
            return false;
        }
        currSecond += 2;
        if (this.warehouse.getFields().get(currFirst).get(currSecond) instanceof Obstacle) {
            //System.out.println("obstacle at " + currFirst + ";"+ currSecond);
            return false;
        } else {
            Space currSpace = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
            if (currSpace.withBox()) {
                //System.out.println("Next Box at " + currSpace.getBox().getCoordinates());
                return currSpace.getBox().moveableRight();
            } else {
                return true;
            }
        }
    }
    public void moveRight() {
        if (!this.moveableRight()) {
            return;
        }
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        Space spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 2);
        if (spaceInQuestion.withBox()) {
            spaceInQuestion.getBox().moveRight();
        }
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 2);
        spaceInQuestion.putBox(this);
        this.coordinates.set(1, currSecond + 1);

    }

    public boolean moveableUp() {
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        if (currFirst == 0) {
            return false;
        }
        currFirst--;
        if (this.warehouse.getFields().get(currFirst).get(currSecond) instanceof Obstacle || this.warehouse.getFields().get(currFirst).get(currSecond + 1) instanceof Obstacle) {
            //System.out.println("obstacle at " + currFirst + ";"+ currSecond);
            return false;
        } else {
            Space currSpaceLeft = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
            Space currSpaceRight = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 1);
            if (currSpaceLeft.withBox()) {
                //System.out.println("Box up left");
                if (currSpaceRight.withBox()) {
                    //System.out.println("Two boxes up, the left one is " + currSpaceRight.getBox().getCoordinates());
                    return (currSpaceRight.getBox().moveableUp() && currSpaceLeft.getBox().moveableUp());
                } else {
                    return  currSpaceLeft.getBox().moveableUp();
                }
            } else {
                if (currSpaceRight.withBox()) {
                    return currSpaceRight.getBox().moveableUp();
                } else {
                    return true;
                }
            }
        }
    }
    public void moveUp() {
        if (!this.moveableUp()) {
            return;
        }
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        Space spaceInQuestionLeft = (Space) this.warehouse.getFields().get(currFirst - 1).get(currSecond);
        Space spaceInQuestionRight = (Space) this.warehouse.getFields().get(currFirst - 1).get(currSecond + 1);
        if (spaceInQuestionLeft.withBox()) {
            spaceInQuestionLeft.getBox().moveUp();
        }
        if (spaceInQuestionRight.withBox()) {
            spaceInQuestionRight.getBox().moveUp();
        }
        Space spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 1);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst - 1).get(currSecond);
        spaceInQuestion.putBox(this);
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst - 1).get(currSecond + 1);
        spaceInQuestion.putBox(this);
        this.coordinates.set(0, currFirst - 1);

    }

    public boolean moveableDown() {
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        if (currFirst == (this.warehouse.getFields().size() - 1)) {
            return false;
        }
        currFirst++;
        if (this.warehouse.getFields().get(currFirst).get(currSecond) instanceof Obstacle || this.warehouse.getFields().get(currFirst).get(currSecond + 1) instanceof Obstacle) {
            //System.out.println("obstacle at " + currFirst + ";"+ currSecond);
            return false;
        } else {
            Space currSpaceLeft = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
            Space currSpaceRight = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 1);
            if (currSpaceLeft.withBox()) {
                //System.out.println("Box up left");
                if (currSpaceRight.withBox()) {
                    //System.out.println("Two boxes up, the left one is " + currSpaceRight.getBox().getCoordinates());
                    return (currSpaceRight.getBox().moveableDown() && currSpaceLeft.getBox().moveableDown());
                } else {
                    return  currSpaceLeft.getBox().moveableDown();
                }
            } else {
                if (currSpaceRight.withBox()) {
                    return currSpaceRight.getBox().moveableDown();
                } else {
                    return true;
                }
            }
        }
    }
    public void moveDown() {
        if (!this.moveableDown()) {
            return;
        }
        int currFirst = this.coordinates.get(0);
        int currSecond = this.coordinates.get(1);
        Space spaceInQuestionLeft = (Space) this.warehouse.getFields().get(currFirst + 1).get(currSecond);
        Space spaceInQuestionRight = (Space) this.warehouse.getFields().get(currFirst + 1).get(currSecond + 1);
        if (spaceInQuestionLeft.withBox()) {
            spaceInQuestionLeft.getBox().moveDown();
        }
        if (spaceInQuestionRight.withBox()) {
            spaceInQuestionRight.getBox().moveDown();
        }
        Space spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst).get(currSecond + 1);
        spaceInQuestion.removeBox();
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst + 1).get(currSecond);
        spaceInQuestion.putBox(this);
        spaceInQuestion = (Space) this.warehouse.getFields().get(currFirst + 1).get(currSecond + 1);
        spaceInQuestion.putBox(this);
        this.coordinates.set(0, currFirst + 1);

    }
    @Override
    public String toString() {
        if (this.isFirstPartPrinted) {
            this.isFirstPartPrinted = false;
            return "]";
        } else {
            this.isFirstPartPrinted = true;
            return "[";
        }
    }



}
