import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {

    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);
        System.out.println("abs1 == abs2 is " + (abs1 == abs2));

        // -2^31 ~ 2^31 -1 이기 떄문에 Integer.MIN_VALUE 의 abs 결과 값은 음수가 출력된다. (-2147483648)
        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println("minInt = " + minInt);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("[old]");
        System.out.println(
                mapOld(numbers, i -> i * 2)
        );
        System.out.println(
                mapOld(numbers, null)
        );

        System.out.println("[new]");
        System.out.println(
                map(numbers, i -> i * 2)
        );
        System.out.println(
                map(numbers, i -> i)
        );
        System.out.println(
                map(numbers, Function.identity())
        );
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

    private static <T, R> List<R> mapOld(List<T> list, Function<T, R> mapper) {
        final List<R> result;
        if (mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list);
        }
        if (result.isEmpty()) {
            for (final T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }
}
