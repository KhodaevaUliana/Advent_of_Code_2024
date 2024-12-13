package org.example;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String fileName = "src/test.txt";
        String fileName = "src/advent_13.txt";
        Parser parser = new Parser(fileName);
        ArrayList<Task> tasks = parser.getTasks();
        //System.out.println(tasks.size());
        //System.out.println(tasks.get(0).solve());
        long res = 0;
        for (Task curr : tasks) {
            Solution currSol = curr.solve();
            //System.out.println(currSol);
            if (currSol != null) {
                res += currSol.getCost();
            }
        }
        System.out.println(res);


    }
}