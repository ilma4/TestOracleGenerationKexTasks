package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    final long RANDOM_SEED_FIRST = 123;
    final long RANDOM_SEED_SECOND = (new Random(RANDOM_SEED_FIRST)).nextLong();
    final int[] TREE_VALUES = new int[]{1, 2, 3, 4, 5, 5, 5, 5};

    BinaryTree firstTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_FIRST), TREE_VALUES);
    BinaryTree secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND), TREE_VALUES);

    @Test
    void contentsSimilarNullCases() {
        assertTrue(BinaryTree.contentsSimilar(null, null));
        assertFalse(BinaryTree.contentsSimilar(firstTree, null));
        assertFalse(BinaryTree.contentsSimilar(null, secondTree));
    }

    @Test
    void contentsSimilarTrue() {
        assertTrue(BinaryTree.contentsSimilar(firstTree, secondTree));
    }

    @Test
    void contentsSimilarFalse() {
        final int[] TREE_VALUES_SECOND = Arrays.stream(TREE_VALUES).map(v -> v + 1).toArray();
        secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND), TREE_VALUES_SECOND);

        assertFalse(BinaryTree.contentsSimilar(firstTree, secondTree));
    }

    @Test
    void contentsSimilarFalseDifferentSize() {
        final int[] TREE_VALUES_SECOND = Arrays.stream(TREE_VALUES).skip(1).toArray();
        secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND), TREE_VALUES_SECOND);

        assertFalse(BinaryTree.contentsSimilar(firstTree, secondTree));
    }

    @Test
    void getValues() {
        final long RANDOM_SEED = 123;
        final int[] TREE_VALUES = new int[]{1, 2, 3, 4, 5, 5, 5, 5};

        Random random = new Random(RANDOM_SEED);
        BinaryTree tree = BinaryTreeImpl.of(random, TREE_VALUES);

        var valuesToCounts = tree.getValuesCounts();

        var expected = Arrays.stream(TREE_VALUES)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertEquals(expected, valuesToCounts);
    }
}