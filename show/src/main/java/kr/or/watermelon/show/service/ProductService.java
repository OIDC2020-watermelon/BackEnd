package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public List<ProductForListDto> searchProductsReleased(String keyword, Category category, Pageable pageable) {
        Page<Product> products;
        if (keyword == null && category == null) {
            products = productRepository.findAll(pageable);
        } else if (keyword == null) {
            products = productRepository.findByCategory(category, pageable);
        } else if (category == null) {
            products = productRepository.findByTitleContaining(keyword, pageable);
        } else {
            products = productRepository.findByTitleContainingAndCategory(keyword, category, pageable);
        }

        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductForListDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }
}
