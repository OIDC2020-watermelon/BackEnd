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
        Place place=placeFactory.savePlace();
        mockMvc.perform(get("/places/"+place.getId()))
                .andExpect(jsonPath("$.name",equalTo(place.getName())));
        //TODO 테스트는 직관적일수록 좋으므로 savePlace("yes24")로 변경하면 좋다.
    }
}