package com.tpavlik.codingcalculator;

public class Item implements Comparable<Item> {

    private Double probability;

    private String bitArray;

    private String resultBit;

    private String name;

    private Item left;

    private Item right;

    public Item(String name, Double probability) {
        this.name = name;
        this.probability = probability;
        this.bitArray = "";
        this.resultBit = "";
    }

    public Item(Item left, Item right, Double probability) {
        this.left = left;
        this.right = right;
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

    public String getResultBit() {
        return resultBit;
    }

    public int countOfBits() {
        return resultBit.length();
    }

    public String getName() {
        return name;
    }

    public Item getLeft() {
        return left;
    }

    public void setLeft(Item left) {
        this.left = left;
    }

    public Item getRight() {
        return right;
    }

    public void setRight(Item right) {
        this.right = right;
    }

    @Override
    public int compareTo(Item item) {
        return -Double.compare(this.getProbability(), item.getProbability());
    }

    @Override
    public String toString() {
        return name + "\t|" + bitArray;
    }
}
