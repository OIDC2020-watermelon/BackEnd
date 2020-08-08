package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.factory.ArtistFactory;
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
public class ArtistControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ArtistController artistController;
    @Autowired
    ArtistFactory artistFactory;

    @DisplayName("아티스트 상세정보 가져오기")
    @Test
    void getArtist() throws Exception {
        Artist tyga = artistFactory.saveItem(Artist.builder()::name, "tyga");

        mockMvc.perform(get("/show/artists/" + tyga.getId()))
                .andExpect(jsonPath("$.name", equalTo(tyga.getName())));
    }

    @DisplayName("아티스트 검색 결과 가져오기")
    @Test
    void searchArtist() throws Exception {
        artistFactory.saveArtists(Artist.builder()::name, Collections.nCopies(2, "cl"));
        artistFactory.saveItem(Artist.builder()::name, "g-dragon");

        mockMvc.perform(get("/show/artists/search?keyword=cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$..name", Every.everyItem(containsString("cl"))));
    }
}