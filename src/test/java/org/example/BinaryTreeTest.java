package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class BinaryTreeTest {

    final long RANDOM_SEED_FIRST = 123;
    final long RANDOM_SEED_SECOND = 321;

    final long BIG_TEST_TREE_SIZE = 10_000;
    final int[] TREE_VALUES_SMALL = new int[]{1, 2, 3, 4, 5, 5, 5, 5};


    BinaryTree firstTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_FIRST), TREE_VALUES_SMALL);
    BinaryTree secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND),
        TREE_VALUES_SMALL);

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
        final int[] TREE_VALUES_SECOND = Arrays.stream(TREE_VALUES_SMALL).map(v -> v + 1).toArray();
        secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND), TREE_VALUES_SECOND);

        assertFalse(BinaryTree.contentsSimilar(firstTree, secondTree));
    }

    @Test
    void contentsSimilarFalseDifferentSize() {
        final int[] TREE_VALUES_SECOND = Arrays.stream(TREE_VALUES_SMALL).skip(1).toArray();
        secondTree = BinaryTreeImpl.of(new Random(RANDOM_SEED_SECOND), TREE_VALUES_SECOND);

        assertFalse(BinaryTree.contentsSimilar(firstTree, secondTree));
    }

    @Test
    void getValues() {
        Random treeRandom = new Random(RANDOM_SEED_FIRST);
        final int[] TREE_VALUES = IntStream
            .generate(() -> treeRandom.nextInt(-100, 100))
            .limit(BIG_TEST_TREE_SIZE)
            .toArray();

        Random random = new Random(RANDOM_SEED_FIRST);
        BinaryTree tree = BinaryTreeImpl.of(random, TREE_VALUES);

        var valuesToCounts = tree.getValuesCounts();

        var expected = Arrays.stream(TREE_VALUES)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertEquals(expected, valuesToCounts);
    }
}