package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.dto.ReqReleaseStatus;
import kr.or.watermelon.show.dto.ResProductDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public Map<String, Object> getPromotionAndThemeRepresentativeProducts() {
        Map<String, Object> promotionThemeProducts = new HashMap<>();
        promotionThemeProducts.put("promotion", productService.getPromotionProducts());
        promotionThemeProducts.put("themes", productService.getThemeRepresentativeProducts());
        return promotionThemeProducts;
    }

    @GetMapping("/search")
    public List<ResProductDto> searchProducts(String keyword, ReqReleaseStatus releaseStatus, Category category,
                                              @PageableDefault(sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.searchProducts(keyword, releaseStatus, category, pageable);
    }
}
