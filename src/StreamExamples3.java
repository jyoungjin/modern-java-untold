import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples3 {
    public static void main(String[] args) {
        System.out.println("collect(toList()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toList())
        );
        System.out.println("collect(toSet()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toSet())
        );
        System.out.println("collect(joining()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", "))
        );

        System.out.println("collect(joining()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(joining(", ", "[", "]"))
        );

        // -128 ~ 127 까지는 캐싱되기 때문에 == 로 가능하지만 범위 밖의 값은 .equals()를 사용해야함.
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i == 3)
                        .findFirst()
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > 1)
                        .count()
        );
    }
}
