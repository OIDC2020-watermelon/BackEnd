package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.factory.CommentFactory;
import kr.or.watermelon.show.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO @RequiredArgsConstructor 왜 Constructor로 Injection이 안될까?
@MockMvcTest
public class ProductControllerTest {//extends AbstractContainerBaseTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentFactory commentFactory;

    @DisplayName("댓글 조회 기능")
    @Test
    void getCommentsByProductId() throws Exception {
        Long productId=new Long(1);
        String content="test";
        Comment commentSaved = commentFactory.saveComment(productId, content);
        mockMvc.perform(get("/products/"+productId+"/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].content",equalTo(content)));
    }

    @DisplayName("댓글 12개 조회 페이지네이션 기능")
    @Test
    void getCommentsByProductId_pagenation_with_12_comments() throws Exception{
        Long productId=new Long(1);
        List<Comment> commentsSaved=commentFactory.saveRandomComments(12,productId);
        mockMvc.perform(get("/products/"+productId+"/comments")
                .param("page","0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(10)))
                .andExpect(jsonPath("$[0].content",equalTo(commentsSaved.get(0).getContent())));

        mockMvc.perform(get("/products/"+productId+"/comments")
                .param("page","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].content",equalTo(commentsSaved.get(10).getContent())));
    }
}
