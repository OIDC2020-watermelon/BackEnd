package kr.or.watermelon.show.controller;


import kr.or.watermelon.show.dto.ResPromotionDto;
import kr.or.watermelon.show.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/")
    public List<ResPromotionDto> getPromotions() {
        return promotionService.getPromotions();
    }
}
