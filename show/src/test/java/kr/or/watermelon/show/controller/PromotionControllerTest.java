package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.factory.PromotionFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class PromotionControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PromotionController promotionController;
    @Autowired
    PromotionFactory promotionFactory;


    @DisplayName("프로모션 리스트 가져오기")
    @Test
    void getPromotions() throws Exception {
        List<String> promotionImgUrls = Arrays.asList("promotion_img_url_1", "promotion_img_url_2");
        promotionFactory.savePromotions(Promotion.builder()::promotionImgUrl, promotionImgUrls);

        mockMvc.perform(get("/show/promotions/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$..promotionImgUrl", equalTo(promotionImgUrls)));
    }
}