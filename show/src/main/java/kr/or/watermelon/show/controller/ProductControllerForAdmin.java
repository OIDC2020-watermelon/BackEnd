package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.*;
import kr.or.watermelon.show.proxy.UserServiceProxy;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Api(tags = {"상품API - 관리자용"})
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/show/products")
public class ProductControllerForAdmin {

    private final ProductService productService;
    @Autowired
    private UserServiceProxy userServiceProxy;

    @GetMapping("/{id}/traffic")
    @ApiOperation(value = "[관리자페이지(p35): 트래픽 통계 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trafficType", allowableValues = "RESERVATION,ACCESS"),
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public List<BucketDto> getProductAnalysis(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @PathVariable Long id, TrafficTypeDto trafficType) throws Exception {
        UserRolesDto user = userServiceProxy.getUserRoles(xAuthToken);
        return productService.getReservationTraffic(user, id, trafficType);
    }

    @PostMapping("/")
    @ApiOperation(value = "[관리자페이지(p35): 공연 생성하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public UUID createProduct(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @RequestBody ProductInfoDto productInfo) throws IOException {
        UserRolesDto user = userServiceProxy.getUserRoles(xAuthToken);
        return productService.createProduct(user, productInfo);
    }

    @DeleteMapping("/{serial}")
    @ApiOperation(value = "[관리자페이지(p35): 공연 삭제하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public void deleteProduct(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @PathVariable UUID serial) throws IOException {
        UserRolesDto user = userServiceProxy.getUserRoles(xAuthToken);
        productService.deleteProduct(user, serial);
    }
}
