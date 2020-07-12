package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.factory.PlaceFactory;
import kr.or.watermelon.show.factory.ThemeFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class PlaceControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PlaceController placeController;
    @Autowired
    PlaceFactory placeFactory;
    @Autowired
    ThemeFactory themeFactory;

    @DisplayName("공연장 상세정보 가져오기")
    @Test
    void getPlace() throws Exception {
        Place place=placeFactory.savePlace("yes24");
        mockMvc.perform(get("/places/"+place.getId()))
                .andExpect(jsonPath("$.name",equalTo("yes24")));
    }

    @DisplayName("공연장 검색 결과 가져오기")
    @Test
    void searchPlace() throws Exception{
        placeFactory.savePlace("yes24_hall");
        placeFactory.savePlace("interpark_hall");
        mockMvc.perform(get("/places/search?keyword=yes24"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",equalTo(1)))
                .andExpect(jsonPath("$[0].name",equalTo("yes24_hall")));
    }
}