package com.tpavlik.codingcalculator;

import java.util.Collections;
import java.util.List;

public class HuffmanMethod {

    private Item[][] resultList;
    int column = 0;

    public Item[][] main(List<Item> items) {

        resultList = new Item[items.size()][items.size()];

        sort(items);
        sumTwoLast(items);
        setBits(items);

        return resultList;
    }

    private void sort(List<Item> items) {
        Collections.sort(items);
    }

    private void sumTwoLast(List<Item> items) {
        sort(items);

        int count = items.size();

        for (int i = 0; i < count; i++) {
            resultList[column][i] = items.get(i);
        }

        if (count <= 2) {
            return;
        }

        Item last1 = items.get(count - 1);
        Item last2 = items.get(count - 2);

        Item newItem = new Item(last1, last2, last1.getProbability() + last2.getProbability());

        items.remove(last1);
        items.remove(last2);

        items.add(newItem);

        column++;
        sumTwoLast(items);
    }

    private void setBits(List<Item> items) {
        Item item1 = items.get(0);
        Item item2 = items.get(1);
        item1.setResultBit("0");
        item2.setResultBit("1");
        setBit(item1);
        setBit(item2);
    }

    private void setBit(Item item) {
        if (item.getLeft() != null) {
            item.getLeft().setResultBit(item.getResultBit() + "1");
            setBit(item.getLeft());
        }
        if (item.getRight() != null) {
            item.getRight().setResultBit(item.getResultBit() + "0");
            setBit(item.getRight());
        }
    }

}
