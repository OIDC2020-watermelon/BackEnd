package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.BucketDto;
import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.dto.ProductInfoDto;
import kr.or.watermelon.show.dto.TrafficTypeDto;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = {"상품API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/show/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/released/search")
    @ApiOperation(value = "[공연예매페이지(p22): 판매 중, 공연 리스트 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", allowableValues = "CONCERT,PLAY,CLASSIC_DANCE,EXHIBITION_EVENT"),
    })
    public List<ProductForListDto> searchProductsReleased(String keyword, Category category) {
        return productService.searchProductsReleased(keyword, category);
    }


    @GetMapping("/search")
    @ApiOperation(value = "[공연검색페이지(p27): 공연 검색 리스트 가져오기")
    public List<ProductForListDto> searchProducts(String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/{id}/traffic")
    @ApiOperation(value = "[관리자페이지(p35): 트래픽 통계 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trafficType", allowableValues = "RESERVATION,ACCESS"),
    })
    public List<BucketDto> getProductAnalysis(@PathVariable Long id, TrafficTypeDto trafficType) throws IOException {
        return productService.getReservationTraffic(id, trafficType);
    }

    @PostMapping("/")
    @ApiOperation(value = "[관리자페이지(p35): 공연 생성하기")
    public ProductInfoDto createProduct(@RequestBody ProductInfoDto productInfo) throws IOException {
        return productService.createProduct(productInfo);
    }

}
