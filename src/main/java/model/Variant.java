package model;

import graph.Node;
import graph.States;

import java.util.*;

import static javax.swing.UIManager.put;

public class Variant {

    private String name;
    private double rValue;
    private Map<States, Double> stateDistribution;
    private double initInfectedPercent;

    public Variant(String name, double rValue, Map<States, Double> stateDistribution, double initInfectedPercent) {
        this.name = name;
        this.rValue = rValue;
        this.stateDistribution = stateDistribution;
        this.initInfectedPercent = initInfectedPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getrValue() {
        return rValue;
    }

    public void setrValue(double rValue) {
        this.rValue = rValue;
    }

    public Map<States, Double> getStateDistribution() {
        return stateDistribution;
    }

    public void setStateDistribution(Map<States, Double> stateDistribution) {
        this.stateDistribution = stateDistribution;
    }

    public double getInitInfectedPercent() {
        return initInfectedPercent;
    }

    public void setInitInfectedPercent(double initInfectedPercent) {
        this.initInfectedPercent = initInfectedPercent;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "name='" + name + '\'' +
                ", rValue=" + rValue +
                ", stateDistribution=" + stateDistribution +
                ", initInfectedPercent=" + initInfectedPercent +
                "}\n";
    }

    public static ArrayList<Variant> loadVariants() {
        ArrayList<Variant> variants = new ArrayList<>();
        variants.add(new Variant("Wuhan", 1.2, new TreeMap<>() {{
            put(States.SYMPTOM_FREE, 0.3);
            put(States.MILD, 0.1);
            put(States.MODERATE, 0.1);
            put(States.SEVERE, 0.3);
            put(States.CRITICAL, 0.2);
        }}, 2));
        variants.add(new Variant("England", 2.3, new TreeMap<>() {{
            put(States.SYMPTOM_FREE, 0.2);
            put(States.MILD, 0.1);
            put(States.MODERATE, 0.1);
            put(States.SEVERE, 0.2);
            put(States.CRITICAL, 0.4);
        }}, 1));

        // TODO: fill with valid data and add more variants

        return variants;
    }

    public static LinkedList<Node> setInitInfected(Map<String, Node> nodeMap, ArrayList<Variant> variants) {
        LinkedList<Node> initInfected = new LinkedList<>();
        int numberToInfect;
        Node currentNode;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            numbers.add(i);
        }
        Random rand = new Random();
        Collections.shuffle(numbers, rand);
        for (Variant variant : variants) {
            numberToInfect = (int) (nodeMap.size() * variant.initInfectedPercent / 100);
            System.out.println("variant: " + variant.getName() + " --> " + numberToInfect);
            for (int i = 0; i < numberToInfect; i++) {
                int randomIndex = numbers.get(0);
                currentNode = nodeMap.get(Integer.toString(randomIndex));
                double tmp = 0;
                double nextRand = rand.nextDouble();
                Iterator<Map.Entry<States, Double>> itr = variant.getStateDistribution().entrySet().iterator();
                while(itr.hasNext()) {
                    Map.Entry<States, Double> entry = itr.next();
                    tmp += entry.getValue();
                    if (nextRand < tmp) {
                        currentNode.setState(entry.getKey());
                        break;
                    }
                }
                currentNode.setVariant(variant);
                initInfected.add(currentNode);
                numbers.remove(0);
            }
        }
        return initInfected;
    }

}
