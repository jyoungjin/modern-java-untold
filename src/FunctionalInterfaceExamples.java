import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {

        /* Functional Interface : Interface 중, abstract 메서드를 하나만 가지는 인터페이스 -> 메서드 람다를 사용할 수 있음 */

        /* 함수형 인터페이스 : Function */
        /* 기본 */
        final Function<String, Integer> toIntBasic = new Function<String, Integer>() {
            @Override
            public Integer apply(final String value) {
                return Integer.parseInt(value);
            }
        };
        /* 람다 */
        final Function<String, Integer> toIntLambda = (value) -> Integer.parseInt(value);
        /* 메서드 참조  */
        final Function<String, Integer> toIntMethodReference = Integer::parseInt;

        final Integer number = toIntMethodReference.apply("100");
        System.out.println("[Function] number = " + number);

        /* Identity Function : 변형 없이 입력 값을 그대로 반환하는 함수 */
        final Function<Integer, Integer> identity = Function.identity();
//        final Function<Integer, Integer> identity = t -> t;
        System.out.println("[Function] identity = " + identity.apply(999));

        /* 함수형 인터페이스 : Consumer */
        final Consumer<String> print = s -> System.out.println("[Consumer] " + s);
        print.accept("consumer method test!!");

        /* 함수형 인터페이스 : Predicate */
        final Predicate<Integer> isPositive = i -> i > 0;
        final Predicate<Integer> isLessThan3 = i -> i < 3;

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = filter(numbers, isPositive);
        List<Integer> lessThanThreeNumbers = filter(numbers, isLessThan3);
        System.out.println("[Predicate] positiveNumbers = " + positiveNumbers);
        System.out.println("[Predicate] lessThanThreeNumbers = " + lessThanThreeNumbers);

        /* 함수형 인터페이스 : Predicate */
        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println("[Supplier] " + helloSupplier.get() + "world");

        long start = System.currentTimeMillis();
        printIfValidIndex(0, () -> getVeryExpensiveValue());
        printIfValidIndex(-1, () -> getVeryExpensiveValue());
        printIfValidIndex(-2, () -> getVeryExpensiveValue());
        long end = System.currentTimeMillis();
        System.out.println("[Supplier] It took " + (end - start) / 1000 + " seconds.");
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

    private static void printIfValidIndex(int num, String value) {
        if (num >= 0) {
            System.out.println("[Supplier] value = " + value);
        } else {
            System.out.println("[Supplier] Invalid");
        }
    }

    // 위 메서드에서는 9초가 걸리지만 아래 메서드에서는 3초가 걸린다 -> 값을 만족하는 경우에 가져오기 때문
    private static void printIfValidIndex(int num, Supplier<String> valueSupplier) {
        if (num >= 0) {
            System.out.println("[Supplier] value = " + valueSupplier.get());
        } else {
            System.out.println("[Supplier] Invalid");
        }
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Let's just say it has very expensive calculation here!
        return "Youngjin";
    }
}
