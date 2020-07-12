package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.factory.ArtistFactory;
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
public class ArtistControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ArtistController artistController;
    @Autowired
    ArtistFactory artistFactory;
    @Autowired
    ThemeFactory themeFactory;

    @DisplayName("아티스트 상세정보 가져오기")
    @Test
    void getArtist() throws Exception {
        Artist artist=artistFactory.saveArtist("tyga");
        mockMvc.perform(get("/artists/"+artist.getId()))
                .andExpect(jsonPath("$.name",equalTo(artist.getName())));
    }

    @DisplayName("공연장 검색 결과 가져오기")
    @Test
    void searchArtist() throws Exception{
        artistFactory.saveArtist("g-dragon");
        artistFactory.saveArtist("CL");
        mockMvc.perform(get("/artists/search?keyword=dragon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",equalTo(1)))
                .andExpect(jsonPath("$[0].name",equalTo("g-dragon")));
    }
}