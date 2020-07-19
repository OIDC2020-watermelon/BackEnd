package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.ResProductForListDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"상품API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    @ApiOperation(value = "[공연예매페이지(p22):공연 리스트 가져오기")
    public List<ResProductForListDto> searchProducts(String keyword, Category category,
                                                     @PageableDefault(sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.searchProducts(keyword, category, pageable);
    }

    }
}
