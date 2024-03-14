import java.math.BigDecimal;

public class CustomFunctionalInterfaceExamples {

    public static void main(String[] args) {

        /* 함수형 인터페이스 : 직접 만드는 함수형 인터페이스 */
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd;
        System.out.println("[Custom] bigDecimalToCurrency = " + bigDecimalToCurrency.toCurrency(BigDecimal.TEN));

        final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String makeString(final T value) {
                return value.toString();
            }
        };
        System.out.println("[Custom] " + anonymousClass.makeString(123));

        // Generic 의 경우 람다식을 사용할 수 없다. [Target method is generic]
//        final InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
//        System.out.println("[Custom] " + invalidFunctionalInterface.makeString(123));
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function3) {
        System.out.println("[Custom] " + function3.apply(t1, t2, t3));
    }
}


@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T> String makeString(T value);
}
