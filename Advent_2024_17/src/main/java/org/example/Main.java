package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Part I
        Computer computer = initialize(scanner);
        computer.execute();
        System.out.println("The answer to Part I");
        System.out.println(computer.getOutput());
        //Part II
        System.out.println("The answer to Part II");
        System.out.println(computer.getBackTraceRes());



    }

    public static Computer initialize(Scanner scanner) {
        System.out.println("Enter the value of register A");
        long regA = Long.valueOf(scanner.nextLine());
        System.out.println("Enter the value of register B");
        int regB = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the value of register C");
        int regC = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the program");
        String[] program = scanner.nextLine().split(",");
        ArrayList<Byte> commands = new ArrayList<>();
        for (String digit : program) {
            commands.add(Byte.valueOf(digit));
        }
        return new Computer(regA, regB, regC, commands);
    }
}