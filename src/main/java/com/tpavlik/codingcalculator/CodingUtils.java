package com.tpavlik.codingcalculator;

import java.util.List;

public class CodingUtils {

    public static double sumDouble(List<Item> items) {
        return items.stream().map(Item::getProbability).mapToDouble(Double::doubleValue).sum();
    }

    public static void validate(List<Item> items) throws Exception {
        if (sumDouble(items) != 1.0f) {
            throw new Exception("Sum of probabilities must be equal to 1");
        }
    }

    public static String codingLength(List<Item> items) {
        double codingWordLength = 0;
        for (Item item : items) {
            codingWordLength += item.getProbability() * item.countOfBits();
        }
        return String.format("%.2f", codingWordLength);
    }

    public static String codeEfficiency(List<Item> items) {
        double entropy = 0;
        double length = 0;
        for (Item item : items) {
            entropy -= item.getProbability() * (Math.log(item.getProbability()) / Math.log(2));
            length += item.getProbability() * item.countOfBits();
        }
        return String.format("%.2f", (entropy / length) * 100);
    }

}
