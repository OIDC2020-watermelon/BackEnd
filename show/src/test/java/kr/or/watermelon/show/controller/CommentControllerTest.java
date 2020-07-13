package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.factory.CommentFactory;
import kr.or.watermelon.show.factory.ProductFactory;
import kr.or.watermelon.show.infra.AbstractContainerBaseTest;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class CommentControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentFactory commentFactory;
    @Autowired
    ProductFactory productFactory;

    @DisplayName("댓글 페이지네이션 조회: 3개")
    @Test
    void getCommentsByProductId_pagenation_with_12_comments() throws Exception {
        List<String> contents = Arrays.asList("댓글1", "댓글2", "댓글3");
        Product product = productFactory.saveProduct();
        Function<String, Comment> contentCommentMaker = (c) -> Comment.builder().product(product).content(c).build();
        List<Comment> commentsSaved = commentFactory.saveComments(contentCommentMaker, contents);

        mockMvc.perform(get("/products/" + product.getId() + "/comments?page=0&size=2")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content", equalTo(commentsSaved.get(0).getContent())));

        mockMvc.perform(get("/products/" + commentsSaved.get(0).getProduct().getId() + "/comments?page=1&size=2")
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", equalTo(commentsSaved.get(2).getContent())));
    }
}
