package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.factory.PlaceFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
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

    @DisplayName("공연장 상세정보 가져오기")
    @Test
    void getPlace() throws Exception {
        Place yes24 = placeFactory.saveItem(Place.builder()::name, "yes24");

        mockMvc.perform(get("/places/" + yes24.getId()))
                .andExpect(jsonPath("$.name", equalTo("yes24")));
    }

    @DisplayName("공연장 검색 결과 가져오기")
    @Test
    void searchPlaces() throws Exception {
        placeFactory.saveItems(Place.builder()::name, Collections.nCopies(2, "yes24"));
        placeFactory.saveItem(Place.builder()::name, "interpark_hall");

        mockMvc.perform(get("/places/search?keyword=yes24"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$..name", Every.everyItem(containsString("yes24"))));
    }
}