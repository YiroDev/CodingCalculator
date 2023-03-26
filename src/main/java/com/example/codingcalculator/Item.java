package com.example.codingcalculator;

public class Item implements Comparable<Item> {

    private Double probability;

    private String bitArray;

    private String name;

    public Item(String name, Double probability) {
        this.name = name;
        this.probability = probability;
        this.bitArray = "";
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

    public int countOfBits() {
        return bitArray.length();
    }

    @Override
    public int compareTo(Item item) {
        return -Double.compare(this.getProbability(), item.getProbability());
    }

    @Override
    public String toString() {
        return name + ": " + bitArray;
    }
}
