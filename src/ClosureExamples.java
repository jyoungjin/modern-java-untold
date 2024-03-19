public class ClosureExamples {
    private int number = 999;

    public static void main(String[] args) {
        new ClosureExamples().test4();
    }

    private void test1() {
        // java 8 이후 부터 final 로 선언하지 않더라도 에러가 나지 않는다.
        // -> why? : final처럼 보이지 않지만 final처럼 동작한다. (effectively final)
        int number = 100;

        /*
            Anonymous Class와 Lambda Expression에서 this 가 가리키는 scope는 다르다!!
            -> anonymous class   : runnable 내부 scope
            -> lambda expression : 해당 class scope
         */
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureExamples.this.number); // 999
                System.out.println(number); // 100
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(this.number));  // 999
        testClosure("Lambda Expression", () -> System.out.println(number));  // 100
    }

    private void test2() {
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public String toString() {
                return "anonymous toString Called!";
            }

            @Override
            public void run() {
                System.out.println("this.toString(): " + this.toString());
            }
        });

        testClosure("Anonymous Class2", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureExamples.this.toString(): " + ClosureExamples.this.toString());
            }
        });

        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString()));
    }

    private void test3() {
        System.out.println("ClosureExamples calling toString(): " + toString());
        System.out.println("ClosureExamples calling toString(): " + toString("Test"));

        testClosure("Anonymous Class2", new Runnable() {
            @Override
            public void run() {
                // anonymous class가 가지고 있는 메서드(상속한 메서드 포함)와 이름이 동일한 외부 메서드에 접근할 경우 shadowing이 발생한다.
//                System.out.println("ClosureExamples.this.toString(): " + toString("Test"));
                System.out.println("toString(String) causes compile-time error.");
                System.out.println("ClosureExamples.this.toString(): " + ClosureExamples.toString("Test"));
            }
        });

        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + toString("Test")));
    }

    private void test4() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                int number = 50; // no compile-time error
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> {
            // int number = 50; // compile-time error
            System.out.println(this.number);
        });
    }

    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("==================================================================");
        System.out.println(name + ": ");
        runnable.run();
        System.out.println("==================================================================");
    }


    @Override
    public String toString() {
        return "ClosureExamples{" +
                "number=" + number +
                '}';
    }

    public static <T> String toString(T value) {
        return "The value is " + String.valueOf(value);
    }
}
