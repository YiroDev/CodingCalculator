package com.example.codingcalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShannonFanoMethod {

    private List<Item> resultList = new ArrayList<>();

    public List<Item> main(List<Item> items) {
        sort(items);
        split(items);

        return resultList;
    }

    private void sort(List<Item> probabilities) {
        Collections.sort(probabilities);
    }

    private void split(List<Item> probabilities) {
        if (probabilities.size() == 1) {
            resultList.add(probabilities.get(0));
            return;
        }
        List<Item> a = new ArrayList<>();
        List<Item> b = probabilities;
        a.add(b.get(0));
        b.remove(0);

        while (true) {
            double firstResult = 0;
            double secondResult = 0;

            double firstA = sumDouble(a);
            double firstB = sumDouble(b);

            firstResult = Math.abs(firstA - firstB);

            double secondA = sumDouble(a) + a.get(0).getProbability();
            double secondB = sumDouble(b) - b.get(0).getProbability();

            secondResult = Math.abs(secondA - secondB);

            if (firstResult <= secondResult) {
                break;
            }
            a.add(b.get(0));
            b.remove(0);
        }
        a.forEach(item -> item.setBit("1"));
        b.forEach(item -> item.setBit("0"));
        split(a);
        split(b);
    }

    private double sumDouble(List<Item> items) {
        return items.stream().map(Item::getProbability).mapToDouble(Double::doubleValue).sum();
    }

    public boolean validate(List<Item> items) {
        if (sumDouble(items) != 1.0f) {
            System.out.println("Sum of probabilities must be equal to 1");
            return false;
        }
        return true;
    }
}
