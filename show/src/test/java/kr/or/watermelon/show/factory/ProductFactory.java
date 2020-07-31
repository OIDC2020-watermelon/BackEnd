package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductFactory {

    private final ProductRepository productRepository;
    private final Factory factory;

    public static Product.ProductBuilder getReservableProductBuilder() {
        return Product.builder()
                .releaseStartTime(LocalDateTime.now().minusDays(1))
                .releaseEndTime(LocalDateTime.now().plusDays(1));
    }

    public <T> List<Product> saveItems(Function<T, CustomBuilder> func, List<T> args) {
        List<Product> products = (List<Product>) factory.makeItems(func, args);
        return productRepository.saveAll(products);
    }

    public <T> Product saveItem(Function<T, CustomBuilder> func, T arg) {
        Product product = (Product) factory.makeItem(func, arg);
        return productRepository.save(product);
    }
}
