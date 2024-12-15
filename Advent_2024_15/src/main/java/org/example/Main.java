package org.example;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_15.txt";
        Parser parser = new Parser(fileName);
        //Solution to the 1st part
        /*Warehouse warehouse = new Warehouse(parser.getBoardCode(), parser.getRobotActions());
        warehouse.execute();
        System.out.println(warehouse.getSumOfGPSCoordinate()); //the answer to Part I*/
        //Solution to the 2nd part
        WarehouseExtended warehouse = new WarehouseExtended(parser.getBoardCode(), parser.getRobotActions());
        warehouse.execute();
        //System.out.println(warehouse);
        System.out.println(warehouse.getSumOfGPSCoordinates()); //the answer to Part II

    }
}