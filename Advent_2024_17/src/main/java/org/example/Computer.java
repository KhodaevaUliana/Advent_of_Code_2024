package org.example;

import java.util.ArrayList;

public class Computer {
    private long regA;
    private long regB;
    private long regC;

    private int pointer;

    private ArrayList<String> sout;

    private ArrayList<Byte> commands;

    private ArrayList<Long> backTraceResults;

    public Computer (long regA, long regB, long regC, ArrayList<Byte> commands) {
        this.regA = regA;
        this.regB = regB;
        this.regC = regC;
        this.commands = commands;
        this.pointer = 0;
        this.sout  = new ArrayList<>();
        this.backTraceResults = new ArrayList<>();
    }



    public void backtrace() {
        backtrace(0L, this.commands.size() - 1);
    }

    public void backtrace(long currRegA, int step) {
        long prevRegALower = currRegA * 8;
        long prevRegAUpper = prevRegALower + 8;
        long output = this.commands.get(step);
        ArrayList<Long> prevRegCand = new ArrayList<>();
        for (long i = prevRegALower; i < prevRegAUpper; i++) {
            if (Computer.regAmetamorphosis(i) == output) {
                prevRegCand.add(i);
            }
        }
        //System.out.println("Step: " + step + "; Candidates: " + prevRegCand);
        int nextStep = step - 1;
        if (prevRegCand.isEmpty()) {
            //System.out.println("Ready!");
            return;
        }
        if (nextStep < 0){
            this.backTraceResults.addAll(prevRegCand);
            return;
        }
        for (long cand : prevRegCand) {
            backtrace(cand, nextStep);
        }

        return;

    }

    public long getBackTraceRes() {
        this.backtrace();
        this.backTraceResults.sort(null);
        return this.backTraceResults.get(0);
    }
    //get possible values in the register A based on the known output
    public static long regAmetamorphosis(long a) {
        long b = a % 8;
        b = b ^ 5;
        long denominator = 1;
        long res = 0;
        long power = b;
        if (power >= 64) {
            res = 0;
        } else {
            for (int i = 0; i < power; i++) {
                denominator *= 2;
            }
            res = a / denominator;
        }
        b = b ^ 6;
        return (b ^ res) % 8;
    }




    public void execute() {
        while (true) {
            if (this.pointer < 0 || this.pointer >= this.commands.size()) {
                //System.out.println("Can't read the opcode");
                break;
            }
            if (this.pointer == this.commands.size() - 1) {
                //System.out.println("Can't read the operand");
                break;
            }
            switch (commands.get(pointer)) {
                case 0:
                    this.adv(this.commands.get(pointer + 1));
                    break;
                case 1:
                    this.bxl(this.commands.get(pointer + 1));
                    break;
                case 2:
                    this.bst(this.commands.get(pointer + 1));
                    break;
                case 3:
                    this.jnz(this.commands.get(pointer + 1));
                    break;
                case 4:
                    this.bxc(this.commands.get(pointer + 1));
                    break;
                case 5:
                    this.out(this.commands.get(pointer + 1));
                    break;
                case 6:
                    this.bdv(this.commands.get(pointer + 1));
                    break;
                case 7:
                    this.cdv(this.commands.get(pointer + 1));
                    break;
                default:
                    System.out.println("Invalid operator");
                    break;
            }
            //System.out.println(this.toString());
        }
    }

    public long combo (byte operand) {
        if (operand >= 0 && operand <= 3) {
            return (long) operand;
        }
        if (operand == 4) {
            return this.regA;
        }
        if (operand == 5) {
            return this.regB;
        }
        if (operand == 6) {
            return this.regC;
        }
        System.out.println("Invalid value of a combo operand");
        return -1;
    }

    public void adv(byte operand) {
        long nominator = this.regA;
        long power = this.combo(operand);
        long denominator = 1;
        long res = 0;
        if (power >= 64) {
            res = 0;
        } else {
            for (int i = 0; i < power; i++) {
                denominator *= 2;
            }
            res = nominator / denominator;
        }
        this.regA = res;
        this.pointer += 2;
    }

    public void bdv(byte operand) {
        long nominator = this.regA;
        long power = this.combo(operand);
        long denominator = 1;
        long res = 0;
        if (power >= 32) {
            res = 0;
        } else {
            for (int i = 0; i < power; i++) {
                denominator *= 2;
            }
            res = nominator / denominator;
        }
        this.regB = res;
        this.pointer += 2;
    }

    public void cdv(byte operand) {
        long nominator = this.regA;
        long power = this.combo(operand);
        long denominator = 1;
        long res = 0;
        if (power >= 32) {
            res = 0;
        } else {
            for (int i = 0; i < power; i++) {
                denominator *= 2;
            }
            res = nominator / denominator;
        }
        this.regC = res;
        this.pointer += 2;
    }

    public void bxl(byte operand) {
        long res = this.regB ^ (long) operand;
        this.regB = res;
        this.pointer += 2;
    }

    public void bst(byte operand) {
        this.regB = this.combo(operand) % 8;
        this.pointer += 2;
    }

    public void jnz(byte operand) {
        if (this.regA != 0) {
            this.pointer = operand;
        } else {
            this.pointer += 2;
        }
    }

    public void bxc (byte operand) {
        long res = this.regB ^ this.regC;
        this.regB = res;
        this.pointer += 2;
    }

    public void out (byte operand) {
        this.sout.add(String.valueOf(this.combo(operand) % 8));
        this.pointer += 2;
        System.out.println(this.toString());
    }

    public String getOutput() {
        return String.join(",", this.sout);
    }

    @Override
    public String toString() {
        return "A: " + this.regA + "; B: " + this.regB + "; C:" + this.regC;

    }


}
