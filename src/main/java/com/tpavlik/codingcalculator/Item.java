package com.tpavlik.codingcalculator;

public class Item implements Comparable<Item> {

    private Double probability;

    private String bitArray;

    private String resultBit;

    private String name;

    public Item(String name, Double probability) {
        this.name = name;
        this.probability = probability;
        this.bitArray = "";
        this.resultBit = "";
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public String getBitArray() {
        return bitArray;
    }

    public void setBit(String bitArray) {
        this.bitArray += bitArray;
    }

    public void setResultBit(String resultBit) {
        this.resultBit += resultBit;
    }

    public int countOfBits() {
        return bitArray.length();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Item item) {
        return -Double.compare(this.getProbability(), item.getProbability());
    }

    @Override
    public String toString() {
        return name + "\t|" + bitArray + "\t(" + resultBit + ")";
    }
}
