package kr.or.watermelon.show.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.PromotionDto;
import kr.or.watermelon.show.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"프로모션API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/")
    @ApiOperation(value = "[메인페이지(p21)]:이벤트 상품 리스트 가져오기")
    public List<PromotionDto> getPromotions() {
        return promotionService.getPromotions();
    }
}
