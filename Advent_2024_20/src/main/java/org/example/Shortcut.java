package org.example;

import java.util.Objects;

public class Shortcut implements Comparable {
    private Coordinate start;
    private Coordinate finish;
    private int saved;


    public Shortcut(Coordinate start, Coordinate finish, int saved) {
        this.start = start;
        this.finish = finish;
        this.saved = saved;
    }


    public int getSaved() {
        return this.saved;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Shortcut shortcut)) return false;
        return saved == shortcut.saved && Objects.equals(start, shortcut.start) && Objects.equals(finish, shortcut.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish, saved);
    }

    @Override
    public int compareTo(Object anotherShortcutAsObject) {
        Shortcut anotherShortcut = (Shortcut) anotherShortcutAsObject;
        if (this.getSaved() < anotherShortcut.getSaved()) {
            return 1;
        } else if (this.getSaved() == anotherShortcut.getSaved()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return this.start + " " + this.finish + " saves " + this.saved + "\n";
    }
}
