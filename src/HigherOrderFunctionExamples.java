import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class HigherOrderFunctionExamples {
    public static void main(String[] args) {
        final Function<Function<Integer, String>, String> f1 = g -> g.apply(10);
        System.out.println(f1.apply(i -> "#" + i)); // "#10"

        final Function<Integer, Function<Integer, Integer>> f2 = i -> i2 -> i + i2;
        System.out.println(f2.apply(10).apply(12)); // 22

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        final List<String> mappedList = map(list, i -> "#" + i);
        System.out.println(mappedList);

        System.out.println(list.stream()
                .map(i -> "#" + i)
                .collect(toList())
        );

//        Function.identity();

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function = i1 -> i2 -> i3 -> i1 + i2 + i3;
        System.out.println(function.apply(1).apply(2).apply(3));

        Function<Integer, Function<Integer, Integer>> plusTen = function.apply(10);
        System.out.println(plusTen.apply(1).apply(1));
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
