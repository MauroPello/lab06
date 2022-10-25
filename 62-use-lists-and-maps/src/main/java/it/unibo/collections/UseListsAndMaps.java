package it.unibo.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    private static final int ELEMS = 1_000;
    private static final int TESTS = 1_000;

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ELEMS; i++) {
            arrayList.add(i + 1000);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        List<Integer> linkedList = new LinkedList<>(arrayList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        Integer tmp = arrayList.get(arrayList.size() - 1);
        arrayList.set(arrayList.size() - 1, arrayList.get(0));
        arrayList.set(0, tmp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer i : arrayList) {
            System.out.println(i);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for (int i = 0; i < TESTS; i++) {
            arrayList.add(0, i);
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("ArrayList took " + time + "ns (" + millis + "ms)");
        
        time = System.nanoTime();
        for (int i = 0; i < TESTS; i++) {
            linkedList.add(0, i);
        }
        System.out.println("LinkedList took " + time + "ns (" + millis + "ms)");
        time = System.nanoTime() - time;
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            arrayList.get(arrayList.size() / 2);
        }
        time = System.nanoTime() - time;
        System.out.println("ArrayList took " + time + "ns (" + millis + "ms)");
        
        time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            linkedList.get(linkedList.size() / 2);
        }
        System.out.println("LinkedList took " + time + "ns (" + millis + "ms)");
        time = System.nanoTime() - time;
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> continents = new HashMap<>();
        continents.put("Africa", 1_110_635_000L);
        continents.put("Americas", 972_005_000L);
        continents.put("Antarctica", 0L);
        continents.put("Asia", 4_298_723_000L);
        continents.put("Europe", 742_452_000L);
        continents.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        long world_population = 0L;
        for (final long population : continents.values()) {
            world_population += population;
        }
        System.out.println("The world population is " + world_population);
    }
}
