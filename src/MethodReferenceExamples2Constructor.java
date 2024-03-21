import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExamples2Constructor {
    public static void main(String[] args) {
        final Section section = new Section(1);

        final Function<Integer, Section> sectionFactoryWithLambdaExpression = number -> new Section(number);
        final Section sectionWithLambdaExpression = sectionFactoryWithLambdaExpression.apply(1);

        final Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
        final Section sectionWithMethodReference = sectionFactoryWithMethodReference.apply(1);

        System.out.println("section = " + section);
        System.out.println("sectionWithLambdaExpression = " + sectionWithLambdaExpression);
        System.out.println("sectionWithMethodReference = " + sectionWithMethodReference);

        System.out.println("\n===============================================================");

        final OldProductMR product = new OldProductMR(1L, "A", new BigDecimal("100"));
        System.out.println(product);

        final OldProductCreator productCreator = OldProductMR::new;
        System.out.println(productCreator.create(1L, "A", new BigDecimal("100")));

        System.out.println("\n===============================================================");

        final ProductMRA a = createProduct(1L, "A", new BigDecimal("123"), ProductMRA::new);
        final ProductMRB b = createProduct(1L, "A", new BigDecimal("123"), ProductMRB::new);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    private static <T extends ProductMR> T createProduct(final Long id, final String name, final BigDecimal price, final ProductCreator<T> productCreator) {
        if (id == null || id < 1L) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given.");
        }

        if (price == null || BigDecimal.ZERO.compareTo(price) > 0) {
            throw new IllegalArgumentException("The price must be greater than 0.");
        }
        return productCreator.create(id, name, price);
    }
}

@FunctionalInterface
interface OldProductCreator {
    OldProductMR create(Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface ProductCreator<T extends ProductMR> {
    T create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}

@AllArgsConstructor
@Data
class OldProductMR {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
abstract class ProductMR {
    private Long id;
    private String name;
    private BigDecimal price;
}


class ProductMRA extends ProductMR {

    public ProductMRA(final Long id, final String name, final BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A=" + super.toString();
    }
}

class ProductMRB extends ProductMR {

    public ProductMRB(final Long id, final String name, final BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B=" + super.toString();
    }
}
