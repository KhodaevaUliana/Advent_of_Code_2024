package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortcutsFinder {
    private ArrayList<Coordinate> path;
    private ArrayList<Shortcut> shortcuts;
    private ArrayList<Shortcut> shortcutsNew;
    private HashMap<Integer, ArrayList<Shortcut>> shortcutsNewStat;
    public static final int maxCheat = 20;

    public ShortcutsFinder(ArrayList<Coordinate> path) {
        this.path = path;
        this.shortcuts = new ArrayList<>();
        this.shortcutsNew = new ArrayList<>();
        this.shortcutsNewStat = new HashMap<>();
    }

    public ArrayList<Shortcut> getShortcuts() {
        this.findShortcuts();
        return this.shortcuts;
    }

    public void findNewShortcuts() {
        int initialLength = this.path.size() - 1;
        for (int i = 0; i < this.path.size() - 1; i++) {
            for (int j = i + 1; j < this.path.size(); j++) {
                int dist = Coordinate.distance(this.path.get(i), this.path.get(j));
                if (dist < maxCheat + 1) {
                    int saved = j - i - dist;
                    if (saved > 0) {
                        Shortcut curr = new Shortcut(this.path.get(i), this.path.get(j), saved);
                        this.shortcutsNew.add(curr);
                        if (!this.shortcutsNewStat.containsKey(saved)) {
                            this.shortcutsNewStat.put(saved, new ArrayList<>());
                        }
                        this.shortcutsNewStat.get(saved).add(curr);
                    }
                }
            }
        }
    }

    public HashMap<Integer, ArrayList<Shortcut>> getShortcutsNewStat() {
        this.findNewShortcuts();
        return this.shortcutsNewStat;
    }

    public void findShortcuts() {
        for (int i = 0; i < this.path.size() - 1; i++) {
            Coordinate curr = this.path.get(i);
            //check above
            int indexJump = this.checkUp(curr);
            if ((indexJump - i) > 2) {
                Coordinate obstacle = new Coordinate(curr.getFirst() - 1, curr.getSecond());
                this.shortcuts.add(new Shortcut(obstacle, this.path.get(indexJump), indexJump - i - 2));
            }
            //check down
            indexJump = this.checkDown(curr);
            if ((indexJump - i) > 2) {
                Coordinate obstacle = new Coordinate(curr.getFirst() + 1, curr.getSecond());
                this.shortcuts.add(new Shortcut(obstacle, this.path.get(indexJump), indexJump - i - 2));
            }
            //check left
            indexJump = this.checkLeft(curr);
            if ((indexJump - i) > 2) {
                Coordinate obstacle = new Coordinate(curr.getFirst(), curr.getSecond() - 1);
                this.shortcuts.add(new Shortcut(obstacle, this.path.get(indexJump), indexJump - i - 2));
            }
            //check right
            indexJump = this.checkRight(curr);
            if ((indexJump - i) > 2) {
                Coordinate obstacle = new Coordinate(curr.getFirst(), curr.getSecond() + 1);
                this.shortcuts.add(new Shortcut(obstacle, this.path.get(indexJump), indexJump - i - 2));
            }
        }
    }

    public int checkUp(Coordinate curr) {
        int first = curr.getFirst();
        int second = curr.getSecond();
        return this.path.indexOf(new Coordinate(first - 2, second));
    }

    public int checkDown(Coordinate curr) {
        int first = curr.getFirst();
        int second = curr.getSecond();
        return this.path.indexOf(new Coordinate(first + 2, second));
    }

    public int checkLeft(Coordinate curr) {
        int first = curr.getFirst();
        int second = curr.getSecond();
        return this.path.indexOf(new Coordinate(first, second - 2));
    }

    public int checkRight(Coordinate curr) {
        int first = curr.getFirst();
        int second = curr.getSecond();
        return this.path.indexOf(new Coordinate(first, second + 2));
    }


}
