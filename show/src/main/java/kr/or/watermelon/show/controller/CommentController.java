package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.or.watermelon.show.dto.CommentDto;
import kr.or.watermelon.show.entity.CommentType;
import kr.or.watermelon.show.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "댓글API", hidden = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/products/{id}/comments/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", required = true, allowableValues = "REVIEW,EXPECTATION,QNA"),
    })
    public List<CommentDto> getProductComments(@PathVariable Long id, @PathVariable CommentType type,
                                               @PageableDefault(sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getCommentsByProductId(id, type, pageable);
    }
}
