package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.entity.ThemeType;
import kr.or.watermelon.show.factory.ProductFactory;
import kr.or.watermelon.show.factory.ThemeFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class ThemeControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ThemeController themeController;
    @Autowired
    ThemeFactory themeFactory;
    @Autowired
    ProductFactory productFactory;

    @DisplayName("테마별 공연 리스트 가져오기")
    @Test
    void getProductsByTheme() throws Exception {
        List<ThemeType> themeTypes = Arrays.asList(ThemeType.NEW, ThemeType.NEW, ThemeType.COMMING_SOON);
        Function<ThemeType, Theme> themeMaker = (t) -> Theme.builder().product(productFactory.saveProduct()).themeType(t).build();
        List<Theme> themes = themeFactory.saveThemes(themeMaker, themeTypes);

        mockMvc.perform(get("/products/themes/NEW"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$", Every.everyItem(hasKey("title"))));
    }
}