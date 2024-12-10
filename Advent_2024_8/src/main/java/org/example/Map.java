package org.example;
import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Field>> fields;
    private ArrayList<Antenna> antennas;

    public Map (ArrayList<ArrayList<Character>> boardCode) {
        this.fields = new ArrayList<>();
        this.antennas = new ArrayList<>();
        for (int i = 0; i < boardCode.size(); i++) {
            ArrayList<Field> newLine = new ArrayList<>();
            for (int j = 0; j < boardCode.get(i).size(); j++) {
                Field newField = new Field(boardCode.get(i).get(j));
                if (newField.isAntenna()) {
                    this.antennas.add(new Antenna(i, j, boardCode.get(i).get(j)));
                }
                newLine.add(newField);

            }
            this.fields.add(newLine);
        }
    }
    public ArrayList<Antenna> getAntennas() {
        return this.antennas;
    }

    public void setAntinodes() {
        for (int i = 0; i < this.antennas.size(); i ++) {
            for (int j = 0; j < this.antennas.size(); j++) {
                if ((i != j) && Antenna.arePair(this.antennas.get(i), this.antennas.get(j))) {
                    Coordinates[] antinodes = Antenna.getAntinodes(this.antennas.get(i), this.antennas.get(j));
                    for (Coordinates r : antinodes) {
                        if (this.checkCoordinateValidity(r)) {
                            this.fields.get(r.getFirstCoordinate()).get(r.getSecondCoordinate()).setAntiNode();
                        }
                    }
                }
            }
        }
    }

    public void setNewAntinodes() {
        for (int i = 0; i < this.antennas.size(); i ++) {
            for (int j = 0; j < this.antennas.size(); j++) {
                if ((i != j) && Antenna.arePair(this.antennas.get(i), this.antennas.get(j))) {
                   ArrayList<Coordinates> currLine = Coordinates.line(this.antennas.get(i).getCoordinates(), this.antennas.get(j).getCoordinates(), this.fields.size());
                   for (Coordinates point : currLine) {
                       this.fields.get(point.getFirstCoordinate()).get(point.getSecondCoordinate()).setAntiNode();
                   }
                }
            }
        }

    }

    public int calculateAntinodes() {
        int res = 0;
        for (int i = 0; i < this.fields.size(); i++) {
            for (int j = 0; j < this.fields.size(); j++) {
                if (this.fields.get(i).get(j).isAntiNode()) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean checkCoordinateValidity (Coordinates r) {
        if (r.getFirstCoordinate() < 0 || r.getFirstCoordinate() >= this.fields.size() || r.getSecondCoordinate() < 0 || r.getSecondCoordinate() >= this.fields.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.fields.size(); i++) {
            for (int j = 0; j < this.fields.get(i).size(); j++) {
                res.append(this.fields.get(i).get(j).toString());
            }
            res.append("\n");
        }
        return res.toString();
    }
}
