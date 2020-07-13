package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ReqReleaseStatus;
import kr.or.watermelon.show.dto.ResProductDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ResProductDto> searchProducts(String keyword, ReqReleaseStatus releaseStatus, Category category, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();//TODO RAW query로 바꾸자
        Page<Product> products;
        if (keyword == null && category == null && releaseStatus == null) {
            products = productRepository.findAll(pageable);
        } else if (keyword == null && releaseStatus == null) {
            products = productRepository.findByCategory(category, pageable);
        } else if (keyword == null && category == null) {
            products = getProductsWithoutKeywordAndCategory(releaseStatus, pageable, now);
        } else if (category == null && releaseStatus == null) {
            products = productRepository.findByTitleContaining(keyword, pageable);
        } else if (keyword == null) {
            products = searchProductsWithoutKeyword(category, releaseStatus, now, pageable);
        } else if (category == null) {
            products = searchProductsWithoutCategory(keyword, releaseStatus, now, pageable);
        } else if (releaseStatus == null) {
            products = productRepository.findByTitleContainingAndCategory(keyword, category, pageable);
        } else {
            products = searchProductsAll(keyword, releaseStatus, category, pageable);
        }

        return products.stream().map(p -> modelMapper.map(p, ResProductDto.class)).collect(Collectors.toList());
    }

    private Page<Product> searchProductsAll(String keyword, ReqReleaseStatus releaseStatus, Category category, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        switch (releaseStatus) {
            case BEFORE_RELEASE:
                return productRepository.findByTitleContainingAndCategoryAndReleaseStartTimeAfter(keyword, category, now, pageable);
            case RELEASE_STARTED:
                return productRepository.findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, category, now, now, pageable);
            case RELEASE_ENDED:
                return productRepository.findByTitleContainingAndCategoryAndReleaseEndTimeBefore(keyword, category, now, pageable);
            default:
                return productRepository.findByTitleContainingAndCategory(keyword, category, pageable);
        }
    }

    private Page<Product> searchProductsWithoutKeyword(Category category, ReqReleaseStatus releaseStatus, LocalDateTime now, Pageable pageable) {
        switch (releaseStatus) {
            case BEFORE_RELEASE:
                return productRepository.findByCategoryAndReleaseStartTimeAfter(category, now, pageable);
            case RELEASE_STARTED:
                return productRepository.findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(category, now, now, pageable);
            case RELEASE_ENDED:
                return productRepository.findByCategoryAndReleaseEndTimeBefore(category, now, pageable);
            default:
                return productRepository.findByCategory(category, pageable);
        }
    }

    private Page<Product> getProductsWithoutKeywordAndCategory(ReqReleaseStatus releaseStatus, Pageable pageable, LocalDateTime now) {
        switch (releaseStatus) {
            case BEFORE_RELEASE:
                return productRepository.findByReleaseStartTimeAfter(now, pageable);
            case RELEASE_STARTED:
                return productRepository.findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(now, now, pageable);
            case RELEASE_ENDED:
                return productRepository.findByReleaseEndTimeBefore(now, pageable);
            default:
                return productRepository.findAll(pageable);
        }
    }

    private Page<Product> searchProductsWithoutCategory(String keyword, ReqReleaseStatus releaseStatus, LocalDateTime now, Pageable pageable) {
        switch (releaseStatus) {
            case BEFORE_RELEASE:
                return productRepository.findByTitleContainingAndReleaseStartTimeAfter(keyword, now, pageable);
            case RELEASE_STARTED:
                return productRepository.findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, now, now, pageable);
            case RELEASE_ENDED:
                return productRepository.findByTitleContainingAndReleaseEndTimeBefore(keyword, now, pageable);
            default:
                return productRepository.findByTitleContaining(keyword, pageable);
        }
    }
}
