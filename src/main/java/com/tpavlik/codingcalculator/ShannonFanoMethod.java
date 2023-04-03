package com.tpavlik.codingcalculator;

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
        for (int i = 0; i < a.size(); i++) {
            if (i == a.size() - 1) {
                a.get(i).setBit("__1__|");
                a.get(i).setResultBit("1");
                continue;
            }
            a.get(i).setBit(String.format("%4s %3s", "1", "|"));
            a.get(i).setResultBit("1");
        }
        for (int i = 0; i < b.size(); i++) {
            if (i == b.size() - 1) {
                b.get(i).setBit("__0__|");
                b.get(i).setResultBit("0");
                continue;
            }
            b.get(i).setBit(String.format("%4s %3s", "0", "|"));
            b.get(i).setResultBit("0");
        }
        split(a);
        split(b);
    }

    public double sumDouble(List<Item> items) {
        return items.stream().map(Item::getProbability).mapToDouble(Double::doubleValue).sum();
    }

    public void validate(List<Item> items) throws Exception {
        if (sumDouble(items) != 1.0f) {
            throw new Exception("Sum of probabilities must be equal to 1");
        }
    }

    public String codingLength(List<Item> items) {
        double codingWordLength = 0;
        for (Item item : items) {
            codingWordLength += item.getProbability() * item.countOfBits();
        }
        return String.format("%.2f", codingWordLength);
    }

    public String codeEffectivity(List<Item> items) {
        double entropy = 0;
        double length = 0;
        for (Item item : items) {
            entropy -= item.getProbability() * (Math.log(item.getProbability()) / Math.log(2));
            length += item.getProbability() * item.countOfBits();
        }
        return String.format("%.2f", (entropy / length) * 100);
    }

    private String underline(String value) {
        return "\033[4m" + value + "\033[0m";
    }
}
