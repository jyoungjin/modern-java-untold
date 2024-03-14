import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceRealExamples {
    public static void main(String[] args) {
        final Product productA = new Product(1L, "A", new BigDecimal("8.00"));
        final Product productB = new Product(2L, "B", new BigDecimal("55.50"));
        final Product productC = new Product(3L, "C", new BigDecimal("23.00"));
        final Product productD = new Product(4L, "D", new BigDecimal("15.00"));
        final Product productE = new Product(5L, "E", new BigDecimal("32.00"));
        final List<Product> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        System.out.println(filter(products, (product) -> product.getPrice().compareTo(BigDecimal.valueOf(20)) >= 0));
        System.out.println(filter(products, (product) -> product.getPrice().compareTo(BigDecimal.TEN) <= 0));

        final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(BigDecimal.valueOf(50)) > 0);
        final List<DiscountedProduct> discountedProducts = map(expensiveProducts, (product) -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(BigDecimal.valueOf(0.5))));
        System.out.println("expensiveProducts = " + expensiveProducts);
        System.out.println("discountedProducts = " + discountedProducts);

        final Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(BigDecimal.valueOf(30)) <= 0;
        // 자식 클래스도 허용하기 위해 filter 메서드의 Predicate<T> -> Predicate<? super T> 로 변경한다.
        System.out.println(filter(discountedProducts, lessThanOrEqualTo30));
        System.out.println(filter(products, lessThanOrEqualTo30));

        System.out.println("total: " + total(products, Product::getPrice));
        System.out.println("discountedTotal: " + total(discountedProducts, Product::getPrice));

        Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, productA, 2),
                new OrderedItem(2L, productB, 1),
                new OrderedItem(3L, productC, 3)
        ));
        System.out.println(order.totalPrice());
    }

    private static <T, R> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal result = BigDecimal.ZERO;
        for (final T t : list) {
            result = result.add(mapper.apply(t));
        }
        return result;
    }


    @Data
    @AllArgsConstructor
    static class Product {
        private Long id;

        private String name;

        private BigDecimal price;
    }

    @ToString(callSuper = true)
    static class DiscountedProduct extends Product {
        public DiscountedProduct(final Long id, final String name, final BigDecimal price) {
            super(id, name, price);
        }
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private Long id;
        private String orderNumber;
        private List<OrderedItem> items;

        public BigDecimal totalPrice() {
            return total(items, OrderedItem::getItemTotal);
        }
    }

    @Data
    @AllArgsConstructor
    static class OrderedItem {
        private Long id;
        private Product product;
        private int quantity;

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }

}
