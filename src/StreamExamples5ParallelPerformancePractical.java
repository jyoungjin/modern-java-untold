import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples5ParallelPerformancePractical {

    private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("25")};
    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);

    private static final List<Product> products;

    static {
        final int length = 8_000_000;
        // ArrayList : 초기에 length가 10인 array를 내부적으로 생성해서 add 메서드 사용 시, 용량 확인을 해서 다 찰때마다 1.5배를 합니다.
//        final List<Product> list = new ArrayList<>();
        final Product[] list = new Product[length];

        for (int i = 1; i <= length; i++) {
            list[i - 1] = new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)]));
        }
//        products = Collections.unmodifiableList(list);
        products = Arrays.asList(list);
    }

    private static BigDecimal imperativeSum(List<Product> products, Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
        return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imperativeTest(BigDecimal targetPrice) {
        System.out.println("============================================================");
        System.out.println("\nImperative Sum\n==========================================");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("============================================================");
    }

    private static void streamTest(BigDecimal targetPrice) {
        System.out.println("============================================================");
        System.out.println("\nStream Sum\n==============================================");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("============================================================");
    }

    private static void parallelStreamTest(BigDecimal targetPrice) {
        System.out.println("============================================================");
        System.out.println("\nParallel Stream Sum\n=====================================");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("============================================================");
    }

    public static void main(String[] args) {
        final BigDecimal targetPrice = new BigDecimal("40");
        parallelStreamTest(targetPrice);
        streamTest(targetPrice);
        imperativeTest(targetPrice);

        System.out.println("\nIgnore Tests Above.");
        System.out.println("Start!!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];
            parallelStreamTest(price);
            streamTest(price);
            imperativeTest(price);
        }
    }
    @AllArgsConstructor
    @Data
    static class Product {
        private Long id;
        private String name;
        private BigDecimal price;
    }
}

