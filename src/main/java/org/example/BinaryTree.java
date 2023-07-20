package org.example;

import java.util.HashMap;
import java.util.Map;

abstract class BinaryTree {

    int value;
    BinaryTree left;
    BinaryTree right;

    static boolean contentsSimilar(BinaryTree lhv, BinaryTree rhv) {
        if (lhv == rhv) {
            return true;
        }
        if (lhv == null || rhv == null || lhv.size() != rhv.size()) {
            return false;
        }

        var leftValuesToCounts = lhv.getValuesCounts();
        var rightValuesToCounts = rhv.getValuesCounts();
        return leftValuesToCounts.equals(rightValuesToCounts);
    }

    Map<Integer, Long> getValuesCounts() {
        Map<Integer, Long> valueToCount = new HashMap<>();
        fillValuesToCounts(valueToCount);
        return valueToCount;
    }

    private void fillValuesToCounts(Map<Integer, Long> accumulator) {
        if (left != null) {
            left.fillValuesToCounts(accumulator);
        }
        accumulator.compute(value, (val, count) -> count == null ? 1 : count + 1);
        if (right != null) {
            right.fillValuesToCounts(accumulator);
        }
    }

    // You can consider that these methods are implemented
    // and you can use them if needed
    abstract boolean contains(int value);

    abstract boolean add(int value);

    abstract boolean remove(int value);

    abstract int size();
}
