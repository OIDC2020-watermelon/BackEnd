package kr.or.watermelon.show.factory;


import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductFactory {

    private final ProductRepository productRepository;

    public <T> Product saveProduct(Function<T, Product> f, T t) {
        Product product = f.apply(t);
        productRepository.save(product);
        return product;
    }

    public <T> List<Product> saveProducts(Function<T, Product> f, List<T> ts) {
        List<Product> products = ts.stream()
                .map(f)
                .collect(Collectors.toList());

        productRepository.saveAll(products);
        Collections.reverse(products);
        return products;
    }

    public Product saveProduct() {
        Product product = productRepository.save(Product.builder().build());
        return product;
    }
}

