package org.example;


import java.util.Arrays;
import java.util.Random;
import java.util.function.IntConsumer;

public class BinaryTreeImpl extends BinaryTree {

    private final Random random;

    BinaryTreeImpl(int value, Random random) {
        this.value = value;
        this.random = random;
    }

    static BinaryTree of(Random random, int... values) {
        var valuesIter = Arrays.stream(values).iterator();
        BinaryTree tree = new BinaryTreeImpl(valuesIter.nextInt(), random);
        valuesIter.forEachRemaining((IntConsumer) tree::add);
        return tree;
    }


    @Override
    boolean contains(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    boolean add(int value) {
        if (random.nextBoolean()) {
            if (left == null) {
                left = new BinaryTreeImpl(value, random);
                return true;
            }
            return left.add(value);
        }
        if (right == null) {
            right = new BinaryTreeImpl(value, random);
            return true;
        }
        return right.add(value);
    }

    @Override
    boolean remove(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    int size() {
        return 1 + (left == null ? 0 : left.size()) + (right == null ? 0 : right.size());
    }
}
