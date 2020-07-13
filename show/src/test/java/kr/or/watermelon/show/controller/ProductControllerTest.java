package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.entity.ThemeType;
import kr.or.watermelon.show.factory.CommentFactory;
import kr.or.watermelon.show.factory.ProductFactory;
import kr.or.watermelon.show.factory.PromotionFactory;
import kr.or.watermelon.show.factory.ThemeFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO @RequiredArgsConstructor 왜 Constructor로 Injection이 안될까?
@MockMvcTest
public class ProductControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentFactory commentFactory;
    @Autowired
    PromotionFactory promotionFactory;
    @Autowired
    ThemeFactory themeFactory;
    @Autowired
    ProductFactory productFactory;

    @DisplayName("댓글 조회 기능")
    @Test
    void getCommentsByProductId() throws Exception {
        Long productId = new Long(1);
        String content = "test";
        Comment commentSaved = commentFactory.saveComment(productId, content);
        mockMvc.perform(get("/products/" + productId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", equalTo(content)));
    }

    @DisplayName("댓글 12개 조회 페이지네이션 기능")
    @Test
    void getCommentsByProductId_pagenation_with_12_comments() throws Exception {
        Long productId = new Long(1);
        List<Comment> commentsSaved = commentFactory.saveRandomComments(12, productId);
        mockMvc.perform(get("/products/" + productId + "/comments")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].content", equalTo(commentsSaved.get(0).getContent())));

        mockMvc.perform(get("/products/" + productId + "/comments")
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content", equalTo(commentsSaved.get(10).getContent())));
    }

    @DisplayName("프로모션&테마별 대표 공연 가져오기")
    @Test
    void getPromotionAndThemeRepresentativeProducts() throws Exception {
        promotionFactory.savePromotions(2);
        themeFactory.saveThemes(5);
        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.promotion..product", hasSize(2)))
                .andExpect(jsonPath("$.promotion..promotionImgUrl", hasSize(2)))
                .andExpect(jsonPath("$.themes.length()", equalTo(ThemeType.values().length)))
                .andExpect(jsonPath("$.themes." + ThemeType.values()[0].name(), hasSize(5)));
    }

    @DisplayName("키워드 공연 검색")
    @Test
    void searchProductsByKeyword() throws Exception {
        Function<String, Product> titleProductMaker = (s) -> Product.builder().title(s).build();
        productFactory.saveProduct(titleProductMaker, "iu-concert1");
        productFactory.saveProduct(titleProductMaker, "iu-concert2");
        productFactory.saveProduct(titleProductMaker, "cl-concert");

        mockMvc.perform(get("/products/search?keyword=iu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$..title", Every.everyItem(containsString("iu"))));
    }

    @DisplayName("카테고리 공연 검색")
    @Test
    void searchProductsByCategory() throws Exception {
        Function<Category, Product> categoryProductMaker = (c) -> Product.builder().category(c).build();
        productFactory.saveProduct(categoryProductMaker, Category.CONCERT);
        productFactory.saveProduct(categoryProductMaker, Category.CONCERT);
        productFactory.saveProduct(categoryProductMaker, Category.PLAY);

        mockMvc.perform(get("/products/search?category=concert"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @DisplayName("판매 시기 별 공연 검색")
    @Test
    void searchProductsByReleaseDate() throws Exception {
        Function<List<LocalDateTime>, Product> categoryProductMaker = (a) -> Product.builder()
                .releaseStartTime(a.get(0))
                .releaseEndTime(a.get(1))
                .build();
        LocalDateTime date = LocalDateTime.now();
        productFactory.saveProduct(categoryProductMaker, Arrays.asList(date.minusDays(2), date.minusDays(1)));
        productFactory.saveProduct(categoryProductMaker, Arrays.asList(date.minusDays(1), date.plusDays(1)));
        productFactory.saveProduct(categoryProductMaker, Arrays.asList(date.plusDays(1), date.plusDays(2)));

        mockMvc.perform(get("/products/search?releaseStatus=BEFORE_RELEASE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }
}
