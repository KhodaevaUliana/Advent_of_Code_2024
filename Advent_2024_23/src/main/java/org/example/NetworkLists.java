package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworkLists {
    //key: the node; value: nodes connected to this key
    private HashMap<String, ArrayList<String>> nodesInfo;

    public NetworkLists(String fileName) {
        this.nodesInfo = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName));) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                String[] currNodes = inputLine.split("-");
                if (!this.nodesInfo.containsKey(currNodes[0])) {
                    this.nodesInfo.put(currNodes[0], new ArrayList<>());
                }
                this.nodesInfo.get(currNodes[0]).add(currNodes[1]);
                if (!this.nodesInfo.containsKey(currNodes[1])) {
                    this.nodesInfo.put(currNodes[1], new ArrayList<>());
                }
                this.nodesInfo.get(currNodes[1]).add(currNodes[0]);
                inputLine = in.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(this.nodesInfo);

    }

    public int calculateConnectedTriples() {
        //first calculate the number of unordered sets
        int cnt = 0;
        for (String node : this.nodesInfo.keySet()) {
            for (int i = 0; i < this.nodesInfo.get(node).size(); i++) {
                for (int j = 0; j < this.nodesInfo.get(node).size(); j++) {
                    if (i != j) {
                        String node1 = this.nodesInfo.get(node).get(i);
                        String node2 = this.nodesInfo.get(node).get(j);
                        if (this.nodesInfo.get(node1).contains(node2)) {
                            System.out.println(node + "," + node1 + "," + node2);
                            cnt++;
                        }
                    }
                }
            }
        }
        //divide by 3! to calculate the number of unique unordered sets
        return cnt / 6;
    }

    //connected triples with nodes beginning with t
    public int calculateConnectedTriplesWithT() {
        //first calculate the number of unordered sets
        int cnt = 0;
        for (String node : this.nodesInfo.keySet()) {
            for (int i = 0; i < this.nodesInfo.get(node).size(); i++) {
                for (int j = 0; j < this.nodesInfo.get(node).size(); j++) {
                    if (i != j) {
                        String node1 = this.nodesInfo.get(node).get(i);
                        String node2 = this.nodesInfo.get(node).get(j);
                        boolean hasT = (node.charAt(0) == 't');
                        if (node1.charAt(0) == 't' || node2.charAt(0) == 't') {
                            hasT = true;
                        }
                        if (hasT && this.nodesInfo.get(node1).contains(node2)) {
                            //System.out.println(node + "," + node1 + "," + node2);
                            cnt++;
                        }
                    }
                }
            }
        }
        //divide by 3! to calculate the number of unique unordered sets
        return cnt / 6;
    }

    //greedy algorithm to find the maximal clique
    public ArrayList<String> findMaxClique() {
        ArrayList<String> maxCliqueGlobal = new ArrayList<>();
        for (String node : this.nodesInfo.keySet())  {
            //find the largest clique containing this node
            ArrayList<String> maxCliqueCurr = new ArrayList<>();
            ArrayList<String> neighbours = this.nodesInfo.get(node);
            for (int i = 0; i < neighbours.size(); i++) {
                //find the largest clique containing the Node and its i-th neighbour
                ArrayList<String> currClique = new ArrayList<>();
                currClique.add(node);
                currClique.add(neighbours.get(i));
                for (int j = i + 1; j < neighbours.size(); j++) {
                    //check whether the j-th neighbour belongs to the clique of the Node and the i-th
                    boolean belongs = true;
                    for (String member : currClique) {
                        if (!this.nodesInfo.get(member).contains(neighbours.get(j))) {
                            belongs = false;
                            break;
                        }
                    }
                    if (belongs) {
                        currClique.add(neighbours.get(j));
                    }
                }
                if (currClique.size() > maxCliqueCurr.size()) {
                    maxCliqueCurr = currClique;
                }
            }
            if (maxCliqueCurr.size() > maxCliqueGlobal.size()) {
                maxCliqueGlobal = maxCliqueCurr;
            }
        }
        return maxCliqueGlobal;
    }
}
