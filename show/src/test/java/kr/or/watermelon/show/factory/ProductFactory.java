package kr.or.watermelon.show.factory;


import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductFactory {

    private final ProductRepository productRepository;

    public <T> Product saveProduct(Function<T, Product> f, T t) {
        Product product = f.apply(t);
        productRepository.save(product);
        return product;
    }

    public Product saveProduct() {
        Product product = productRepository.save(Product.builder().build());
        return product;
    }

}

