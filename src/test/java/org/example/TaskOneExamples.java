package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NewClassNamingConvention")
public class TaskOneExamples {

    @Test
    void exampleArrays() {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{1, 2, 3};

        assertFalse(a.equals(b));
        assertTrue(Arrays.equals(a, b));
    }

    @Test
    void exampleMultidimensionalArrays() {
        int[][] a = new int[][]{{1, 2}, {3, 4}};
        int[][] b = new int[][]{{1, 2,}, {3, 4}};

        assertFalse(a.equals(b));
        assertFalse(Arrays.equals(a, b));
        assertTrue(Arrays.deepEquals(a, b));
    }

    @Test
    void exampleDoubles() {
        Double a = 0.3;
        Double b = 0.2 + 0.1;

        assertFalse(a.equals(b));
        assertTrue(Math.abs(a - b) < 1e-8);
    }
}
