package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import sun.misc.Unsafe;

public class Main {

    static record Rec(int a, Object b) {

    }

    static class Tst {

    }

    static class Base {

        protected int a;

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj.getClass() == Base.class;
        }
    }

    static class Der1 extends Base {

        private int b;

        @Override
        public boolean equals(Object obj) {
            return obj.getClass() == Der1.class;
        }
    }

    public static void main(String[] args) {
        Double a = 0.3;
        Double b = 0.2 + 0.1;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    static void example() {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{1, 2};
        System.out.println(a.equals(b)); // false
        System.out.println(Arrays.equals(a, b)); // true

        int[][] m = new int[][]{{1}, {2}};
        int[][] n = new int[][]{{1}, {2}};
        System.out.println(m.equals(n)); // false
        System.out.println(Arrays.equals(m, n)); // false
        System.out.println(Arrays.deepEquals(m, n)); // true

        Object k = new Object();
        List<Integer> asdf = new ArrayList<>();
    }

}