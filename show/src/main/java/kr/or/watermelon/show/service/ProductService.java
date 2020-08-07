package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public List<ProductForListDto> searchProductsReleased(String keyword, Category category) {
        List<Product> products;
        LocalDateTime now = LocalDateTime.now();
        if (keyword == null && category == null) {
            products = productRepository.findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(now, now);
        } else if (keyword == null) {
            products = productRepository.findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(category, now, now);
        } else if (category == null) {
            products = productRepository.findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, now, now);
        } else {
            products = productRepository.findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, category, now, now);
        }

        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductForListDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByNameContaining(keyword);
        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }
}
