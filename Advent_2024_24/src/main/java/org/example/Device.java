package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Device {

    private HashMap<String, Boolean> wireValue;
    private ArrayList<Operation> operations;
    private ArrayList<String> wiresZ; //zN, zN-1,...z00
    private ArrayList<String> wiresY;
    private ArrayList<String> wiresX;
    private ArrayList<String> broken;//registers to be swapped
    private HashMap<String, Operation> history;//save history of operations affecting this wire

    public Device (String fileName) {
        this.wireValue = new HashMap<>();
        this.operations = new ArrayList<>();
        this.history = new HashMap<>();
        this.broken = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            //read off initial values
            while (!inputLine.isEmpty()) {
                String[] input = inputLine.split(" ");
                String wireName = input[0].substring(0, input[0].length() - 1);
                boolean value = (Integer.valueOf(input[1]) == 1);
                this.wireValue.put(wireName, value);
                inputLine = in.readLine();
            }
            inputLine = in.readLine();
            //read off and execute operations
            while (inputLine != null) {
                String[] input = inputLine.split(" ");
                this.operations.add(new Operation(input[0], input[1], input[2], input[4]));
                inputLine = in.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long x = this.extractX();
        long y = this.extractY();

    }

    public void run() {
        while (!this.operations.isEmpty()) {
            Iterator<Operation> itr = this.operations.iterator();
            //if operation is executable, execute it and delete it from out to-do-list
            while (itr.hasNext()) {
                Operation currOperation = itr.next();
                if (currOperation.execute(this.wireValue)) {
                    this.history.put(currOperation.getResult(), currOperation);
                    itr.remove();
                }
            }
        }
        //System.out.println(this.wireValue);
    }

    public long extractZ() {
        //find wires starting with z
        ArrayList<String> wiresZ = new ArrayList<>();
        for (String wire : this.wireValue.keySet()) {
            if (wire.charAt(0) == 'z') {
                wiresZ.add(wire);
            }
        }
        Collections.sort(wiresZ, Collections.reverseOrder());
        this.wiresZ = wiresZ;
        //System.out.println(wiresZ);
        StringBuilder numberBinaryStrBuilder = new StringBuilder();
        for (String wire : wiresZ) {
            numberBinaryStrBuilder.append(this.wireValue.get(wire) ? 1 : 0);
        }
        String numberBinaryStr = numberBinaryStrBuilder.toString();
        //System.out.println(numberBinaryStr);
        return Long.parseLong(numberBinaryStr, 2);
    }

    public long extractX() {
        //find wires starting with x
        ArrayList<String> wiresX = new ArrayList<>();
        for (String wire : this.wireValue.keySet()) {
            if (wire.charAt(0) == 'x') {
                wiresX.add(wire);
            }
        }
        Collections.sort(wiresX, Collections.reverseOrder());
        this.wiresX = wiresX;
        //System.out.println(wiresX);
        StringBuilder numberBinaryStrBuilder = new StringBuilder();
        for (String wire : wiresX) {
            numberBinaryStrBuilder.append(this.wireValue.get(wire) ? 1 : 0);
        }
        String numberBinaryStr = numberBinaryStrBuilder.toString();
        //System.out.println(numberBinaryStr);
        return Long.parseLong(numberBinaryStr, 2);
    }
    public long extractY() {
        //find wires starting with y
        ArrayList<String> wiresY = new ArrayList<>();
        for (String wire : this.wireValue.keySet()) {
            if (wire.charAt(0) == 'y') {
                wiresY.add(wire);
            }
        }
        Collections.sort(wiresY, Collections.reverseOrder());
        this.wiresY = wiresY;
        //System.out.println(wiresY);
        StringBuilder numberBinaryStrBuilder = new StringBuilder();
        for (String wire : wiresY) {
            numberBinaryStrBuilder.append(this.wireValue.get(wire) ? 1 : 0);
        }
        String numberBinaryStr = numberBinaryStrBuilder.toString();
        //System.out.println(numberBinaryStr);
        return Long.parseLong(numberBinaryStr, 2);
    }

    public void setDifferentValues() {
        for (String wire : this.wireValue.keySet()) {
            double rand = Math.random();
            this.wireValue.put(wire, (rand < 0.5));
        }
    }

    public ArrayList<String> getSuspicious() {
        ArrayList<String> sus = new ArrayList<>();
        long x = this.extractX();
        long y = this.extractY();
        long z = this.extractZ();
        String diff = Long.toBinaryString((x + y) ^ z);
        for (int i = 0; i < diff.length(); i++) {
            if (diff.charAt(diff.length() - 1 - i) == '1') {
                sus.add(this.wiresZ.get(i));
            }
        }
        return sus;
    }

    public ArrayList<String> getWiresZ() {
        return this.wiresZ;
    }



    public void checkZ() {
        //here we check only bits in the middle:
        //the least and the second least significant bits and the largest one
        //should be checked separately. However, since in my input these bits
        //work properly, this part is left out.

        //Here, we check whether addition is done correctly
        for (int i = 2; i < this.wiresZ.size() - 1; i++) {
            String wire = this.wiresZ.get(this.wiresZ.size() - 1 - i);
            String firstArg = this.history.get(wire).getFirstArg();
            String secondArg = this.history.get(wire).getSecondArg();
            if (!this.history.get(wire).getOperator().equals("XOR")) {
                /*System.out.println("Problem with " + wire + ": no first XOR");
                System.out.println(this.history.get(wire));
                System.out.println();*/
                this.broken.add(wire);
                continue;
            }
            String additionArg;
            String transferArg;
            //System.out.println(wire);
            //find addition
            Operation addition = new Operation(this.wiresX.get(this.wiresX.size() - 1 - i), "XOR", this.wiresY.get(this.wiresY.size() - 1 - i), "");
            if ((this.history.get(secondArg) != null && this.history.get(secondArg).equals(addition)) || (this.history.get(firstArg) != null && this.history.get(firstArg).equals(addition))) {
                if (this.history.get(secondArg) != null && this.history.get(secondArg).equals(addition)) {
                    additionArg = secondArg;
                    transferArg = firstArg;
                } else {
                    additionArg = firstArg;
                    transferArg = secondArg;
                }
                //System.out.println("OK");
            } else {
                /*System.out.println("Problem with " + wire + ": no correct addition arg");
                System.out.println(this.history.get(wire));
                System.out.println(this.history.get(firstArg));
                System.out.println(this.history.get(secondArg));
                System.out.println();*/
                this.broken.add(secondArg);
                continue;
            }
            if (this.history.get(transferArg) == null) {
                /*System.out.println("Problem with " + wire + ": no correct transfer");
                System.out.println(this.history.get(wire));
                System.out.println();*/
                this.broken.add(transferArg);
                continue;
            }

            firstArg = this.history.get(transferArg).getFirstArg();
            secondArg = this.history.get(transferArg).getSecondArg();
            if (!this.history.get(transferArg).getOperator().equals("OR")) {
                /*System.out.println("Problem with " + wire + ": no correct transfer");
                System.out.println(this.history.get(transferArg));
                System.out.println();*/
                this.broken.add(transferArg);
                continue;
            }
            Operation transAnd = new Operation(this.wiresX.get(this.wiresX.size() - i), "AND", this.wiresY.get(this.wiresY.size() - i), "");
            if ((this.history.get(secondArg) != null && this.history.get(secondArg).equals(transAnd)) || (this.history.get(firstArg) != null && this.history.get(firstArg).equals(transAnd))) {
                if (this.history.get(secondArg) != null && this.history.get(secondArg).equals(transAnd)) {
                    additionArg = secondArg;
                    transferArg = firstArg;
                } else {
                    additionArg = firstArg;
                    transferArg = secondArg;
                }
                //System.out.println("OK");
            } else {
                /*System.out.println("Problem with " + wire + ": no transfer addition");
                System.out.println(this.history.get(transferArg));
                System.out.println(this.history.get(firstArg));
                System.out.println(this.history.get(secondArg));
                System.out.println();*/
                this.broken.add(secondArg);
                continue;
            }
            //check recursive transfer
            String prevWire = this.wiresZ.get(this.wiresZ.size() - i);
            //System.out.println(prevWire);
            String prevWirefirstArg = this.history.get(prevWire).getFirstArg();
            String prevWiresecondArg = this.history.get(prevWire).getSecondArg();
            Operation recursTrans = new Operation(prevWirefirstArg, "AND", prevWiresecondArg, "");
            if (this.history.get(transferArg) == null) {
                /*System.out.println("Problem with " + wire + ": no recursive transfer");
                System.out.println(transferArg);
                System.out.println();*/
                this.broken.add(transferArg);
                continue;
            }
            if (!this.history.get(transferArg).equals(recursTrans)) {
                /*System.out.println("Problem with " + wire + ": incorrect recursive transfer");
                System.out.println(this.history.get(transferArg));
                System.out.println(recursTrans);
                System.out.println();*/
                this.broken.add(transferArg);
                continue;
            }


        }
        //System.out.println(this.broken);
    }

    public String getWiresToBeSwapped() {
        this.checkZ();
        this.broken.sort(null);
        return String.join(",", this.broken);
    }
}
