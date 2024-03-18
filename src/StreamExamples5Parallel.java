import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamExamples5Parallel {
    public static void main(String[] args) {

        // Anonymous Class(익명 클래스)의 경우 class 외부의 variable에 접근하기 위해서는 그 variable이 final variable이어야만 합니다.
        // array의 경우 array 자체가 object이고 그 안의 값 변경은 array object의 상태변경이라고 생각하시면 됩니다
        final int[] sum = {0};
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);

        System.out.println("sum: " + sum[0]);

        System.out.println("\n==============================================");
        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("parallel sum (with side-effect): " + sum2[0]);

        System.out.println("\n==============================================");
        System.out.println("stream sum (no side-effect): " +
                IntStream.range(0, 100)
                        .sum()
        );

        System.out.println("\n==============================================");
        System.out.println("parallel stream sum (no side-effect): " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum()
        );

        System.out.println("\n==============================================");
        System.out.println("Stream");
        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                }).forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("\n==============================================");
        System.out.println("Parallel Stream");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                }).forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start2);

        System.out.println("\n==============================================");
        System.out.println("Parallel Stream with parallelism");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        System.out.println("getParallelism=" + ForkJoinPool.commonPool().getParallelism());

        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                }).forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start3);

    }
}
