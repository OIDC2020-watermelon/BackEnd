package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.CommentType;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class CommentControllerTest extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentController commentController;
    @Autowired
    CommentFactory commentFactory;
    @Autowired
    ProductFactory productFactory;

    @DisplayName("공연 댓글 리스트 가져오기")
    @Test
    void getComments() throws Exception {
        Product product = productFactory.saveItem(Product.builder()::title, "");
        List<CommentType> commentTypes = Arrays.asList(CommentType.QNA, CommentType.QNA, CommentType.EXPECTATION);
        commentFactory.saveItems(Comment.builder().product(product)::type, commentTypes);

        mockMvc.perform(get("/products/" + product.getId() + "/comments/QNA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}