import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class MethodReferenceExamples {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);
//                .forEach(i -> System.out.println(i));

        System.out.println(
                Arrays.asList(new BigDecimal("10"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .sorted(BigDecimalUtil::compare)
                        .collect(toList())
        );

        System.out.println(
                Arrays.asList(new BigDecimal("10"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .sorted(BigDecimal::compareTo)
                        .collect(toList())
        );

        List<String> abcdlist = Arrays.asList("a", "b", "c", "d");
        final String targetString = "c";
        System.out.println("abcdlist = " + abcdlist);
        System.out.println("targetString = " + targetString);
        System.out.println(
                abcdlist
                        .stream()
//                        .anyMatch(x -> x.equals("c"))
                        .anyMatch(targetString::equals)
        );

        System.out.println("\n===============================================================");
        methodReference03();
    }

    private static void methodReference03() {
        /* First Class Function */

        /**
         * [1] Function can be passed as a parameter to another function
         */
        /*
         * Using Lambda Expression
         */
        System.out.println(testFirstClassFunction1(3, i -> String.valueOf(i * 2)));
        /*
         * Using Method Reference
         */
        System.out.println(testFirstClassFunction1(3, MethodReferenceExamples::doubleThenToString));

        /**
         * [2] A function can be returned as the result of another function.
         */
        /*
         * Using Lambda Expression
         */
        final Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        System.out.println(fl.apply(3));
        /*
         * Using Method Reference
         */
        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        System.out.println(fmr.apply(3));

        /**
         * A function can be stored in the data structure.
         */
        /*
         * Using Lambda Expression
         */
        final List<Function<Integer, String>> fsL = Arrays.asList(i -> String.valueOf(i * 2));
        for (final Function<Integer, String> f : fsL) {
            final String result = f.apply(3);
            System.out.println("result = " + result);
        }
        /*
         * Using Method Reference
         */
        final List<Function<Integer, String>> fmrL = Arrays.asList(MethodReferenceExamples::doubleThenToString);
        for (final Function<Integer, String> f : fmrL) {
            final String result = f.apply(3);
            System.out.println("result = " + result);
        }

        System.out.println("\n===============================================================");
        /*
         * Using Lambda Expression
         */
        final Function<Integer, String> fl2 = i -> String.valueOf(i * 2);
        final String resultFl2 = fl2.apply(3);
        System.out.println("resultFl2 = " + resultFl2);
        /*
         * Using Method Reference
         */
        final Function<Integer, String> fmr2 = MethodReferenceExamples::doubleThenToString;
        final String resultFmr2 = fmr2.apply(3);
        System.out.println("resultFmr2 = " + resultFmr2);

        /*
         * Both Lambda Expression and Method Reference
         */
        final List<Function<Integer, String>> fsBoth = Arrays.asList(
                i -> String.valueOf(i * 2),
                MethodReferenceExamples::doubleThenToString,
                MethodReferenceExamples::addHashPrefix
        );
        for (final Function<Integer, String> f : fsBoth) {
            final String result = f.apply(7);
            System.out.println("result = " + result);
        }
    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static String addHashPrefix(int i) {
        return "#" + i;
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples::doubleThenToString;
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}
