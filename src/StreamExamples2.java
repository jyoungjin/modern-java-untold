import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples2 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = null;
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result: " + result);

        System.out.println("Functional Result: " +
                numbers.stream()
                        .filter(number -> {
                            System.out.println("number > 3");
                            return number > 3;
                        })
                        .filter(number -> {
                            System.out.println("number < 9");
                            return number < 9;
                        })
                        .map(number -> {
                            System.out.println("number * 2");
                            return number * 2;
                        })
                        .filter(number -> {
                            System.out.println("number > 2");
                            return number > 10;
                        })
                        .findFirst()
                        .orElse(0)
        );

        // 아래 방식에 비해 Stream을 사용하면 불필요한 연산을 줄일 수 있다.
        final AtomicInteger count = new AtomicInteger(0);

        final List<Integer> greaterThan3 = filter(numbers, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 3");
            return i > 3;
        });
        final List<Integer> lessThan9 = filter(greaterThan3, i -> {
            System.out.println(count.getAndAdd(1) + ": i < 9");
            return i < 9;
        });
        final List<Integer> doubled = map(lessThan9, i -> {
            System.out.println(count.getAndAdd(1) + ": i * 2");
            return i * 2;
        });
        final List<Integer> greaterThan10 = filter(doubled, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 10");
            return i > 10;
        });
        System.out.println("예전 방식: " + greaterThan10.get(0) + ", 연산 횟수: " + count.get());
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
