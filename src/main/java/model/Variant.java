package model;

import graph.Node;
import graph.States;

import java.util.*;

public class Variant {

    private String name;
    private double rValue;
    private double[] stateDistribution;
    private double initInfectedPercent;

    public Variant(String name, double rValue, double[] stateDistribution, double initInfectedPercent) {
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

    public double[] getStateDistribution() {
        return stateDistribution;
    }

    public void setStateDistribution(double[] stateDistribution) {
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
                ", stateDistribution=" + Arrays.toString(stateDistribution) +
                ", initInfectedPercent=" + initInfectedPercent +
                '}';
    }

    public static ArrayList<Variant> loadVariants() {
        ArrayList<Variant> variants = new ArrayList<>();
        variants.add(new Variant("Wuhan", 1.2, new double[]{0.3, 0.1, 0.1, 0.3, 0.2}, 2));
        variants.add(new Variant("England", 2.3, new double[]{0.2, 0.1, 0.1, 0.2, 0.4}, 1));
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
                currentNode.setState(States.INFECTED);
                currentNode.setVariant(variant);
                initInfected.add(currentNode);
                numbers.remove(0);
            }
        }
        return initInfected;
    }

}
