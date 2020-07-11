package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.dto.CommentResponse;
import kr.or.watermelon.show.service.CommentService;
import kr.or.watermelon.show.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final CommentService commentService;
    private final ProductService productService;

    @GetMapping("/{productId}/comments")
    public List<CommentResponse> getCommentsByProductId(@PathVariable Long productId,
                                                        @PageableDefault(sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getCommentsByProductId(productId,pageable);
    }

    @GetMapping("/")
    public Object getPromotionAndThemeRepresentativeProducts(){
        HashMap<String, Object> promotionThemeProducts = new HashMap<>();
        promotionThemeProducts.put("promotion",productService.getPromotionProducts());
        return promotionThemeProducts;
    }
}
