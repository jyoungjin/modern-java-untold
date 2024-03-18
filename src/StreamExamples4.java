import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class StreamExamples4 {
    public static void main(String[] args) {
        final List<Product> products =
                Arrays.asList(
                        new Product(1L, "A", BigDecimal.valueOf(100.50)),
                        new Product(2L, "B", BigDecimal.valueOf(23.00)),
                        new Product(3L, "C", BigDecimal.valueOf(31.45)),
                        new Product(4L, "D", BigDecimal.valueOf(80.20)),
                        new Product(5L, "E", BigDecimal.valueOf(7.50))
                );

        System.out.println("Products.price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(30)) >= 0)
                        .collect(toList())
        );

        System.out.println("\n==============================================");
        System.out.println("Products.price >= 30(joining): \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(30)) >= 0)
                        .map(product -> product.toString())
                        .collect(joining("\n"))
        );

        System.out.println("\n==============================================");
        System.out.println("IntStream.sum: " +
                IntStream.of(1, 2, 3, 4, 5)
                        .sum()
        );

        System.out.println("\n==============================================");
        System.out.println("Total Price: " +
                products.stream()
                        .map(Product::getPrice)
                        // reduce : element 하나하나 줄여나가서 하나만 남기기 때문
                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n==============================================");
        System.out.println("Total Price of Products.price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(30.00)) >= 0)
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        System.out.println("\n==============================================");
        System.out.println("# of Products.price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(30.00)) >= 0)
                        .map(Product::getPrice)
                        .count()
        );

        System.out.println("\n==============================================");
        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem item3 = new OrderedItem(3L, products.get(4), 10);
        final Order order = new Order(1L, Arrays.asList(item1, item2, item3));
        System.out.println("order.totalPrice(): " + order.totalPrice());
    }
}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@AllArgsConstructor
@Data
class Order {
    private Long id;
    private List<OrderedItem> items;

    public BigDecimal totalPrice() {
        return items.stream()
                .map(OrderedItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
