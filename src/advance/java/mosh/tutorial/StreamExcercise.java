package advance.java.mosh.tutorial;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExcercise {

    public void run() {
//        waysToCreateStream();
//        streamMapping();
//        streamSlicing();
//        streamDistinct();
        streamReducers();
    }

    public void streamReducers() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        var count = list.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Number of even numbers: " + count);

        boolean anyOdd = list.stream()
                .anyMatch(n -> n % 2 == 1);
        System.out.println(anyOdd ? "Odd number exists in the list" : "Odd number does not exists in the list");

        boolean allOdd = list.stream()
                .allMatch(n -> n % 2 == 1);
        System.out.println(allOdd ? "All elements are odd" : "All elements are not odd");

        int result = list.stream()
                .max(Integer::compareTo)
                .get();
        System.out.println("Max of list: " + result);

        list.stream()
//                .reduce((a, b) -> a + b)
                .reduce(Integer::sum)
                .ifPresentOrElse(System.out::println, () -> System.out.println(0));
        }

    public void streamDistinct() {
        Stream.of(1, 2, 3, 3, 1, 3, 2, 4, 5, 6, 7)
                .distinct()
                .forEach(System.out::println);
        // can we do that with toSet() ?
        Stream.of(1, 2, 3, 3, 1, 3, 2, 4, 5, 6, 7)
                .collect(Collectors.toSet()) // yep, we can
                .forEach(System.out::println);
    }

    public void streamSlicing () {
        Stream.of(1, 3, 5, 2, 4, 6)
                .takeWhile(n -> n % 2 == 1)
                .forEach(System.out::println);
        Stream.of(1, 3, 5, 2, 4, 6)
                .dropWhile(n -> n % 2 == 1)
                .forEach(System.out::println);
    }

    public void streamMapping () {
        Stream.iterate(1, n -> n + 1)
                .limit(20)
                .filter(n -> n % 2 == 0)
                .mapToDouble(n -> (double)n)
                .forEach(System.out::println);

        // merging even and odd list
        List<List<Integer>> listOfInt = List.of(List.of(1, 3, 5), List.of(2, 4, 6));
        listOfInt.stream()
                .flatMap(Collection::stream)
                .forEachOrdered(System.out::println);
    }

    public void waysToCreateStream() {
        // There are three ways we can create stream
        // From collections
        List<Integer> list = List.of(5, 2, 3, 1, 5, 2, 9);
        list = list.stream()
                .sorted(Collections.reverseOrder())
                .toList();
        System.out.println(list);

        // from arrays
        int[] arr = {3, 2, 5, 1, 3};
        arr = Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .toArray();
        System.out.println("Only even numbers: " + Arrays.toString(arr));

        // We can make infinite stream using generate or iterate method
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(2, n -> n * n)
                .limit(5)
                .forEach(System.out::println);
    }
}