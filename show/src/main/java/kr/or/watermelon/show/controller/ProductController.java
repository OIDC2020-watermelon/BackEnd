package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", allowableValues = "CONCERT,PLAY,CLASSIC_DANCE,EXHIBITION_EVENT"),
    })
    public List<ProductForListDto> searchProductsReleased(String keyword, Category category) {
        return productService.searchProductsReleased(keyword, category);
    }


    @GetMapping("/")
    @ApiOperation(value = "[공연검색페이지(p27): 공연 검색 리스트 가져오기")
    public List<ProductForListDto> searchArtists(String keyword) {
        return productService.searchProducts(keyword);
    }
}
