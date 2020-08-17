package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.or.watermelon.show.dto.CommentDto;
import kr.or.watermelon.show.dto.ReqCommentDto;
import kr.or.watermelon.show.dto.UserIdDto;
import kr.or.watermelon.show.entity.CommentType;
import kr.or.watermelon.show.proxy.UserServiceProxy;
import kr.or.watermelon.show.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "댓글API", hidden = true)
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/show")
public class CommentController {

    private final CommentService commentService;
    @Autowired
    private UserServiceProxy userServiceProxy;

    @GetMapping("/products/{id}/comments/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", required = true, allowableValues = "REVIEW,EXPECTATION,QNA"),
    })
    public List<CommentDto> getProductComments(@PathVariable Long id, @PathVariable CommentType type,
                                               @PageableDefault(sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getCommentsByProductId(id, type, pageable);
    }

    @PostMapping("/products/{id}/comments/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", required = true, allowableValues = "REVIEW,EXPECTATION,QNA"),
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public CommentDto registerComment(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @PathVariable Long id, @PathVariable CommentType type, @RequestBody ReqCommentDto reqCommentDto) {
        UserIdDto user = userServiceProxy.getUserId(xAuthToken);
        return commentService.registerComment(id, user, type, reqCommentDto);
    }

    @PutMapping("/comments/{commentId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public CommentDto modifyComment(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @PathVariable Long commentId, @RequestBody ReqCommentDto reqCommentDto) {
        UserIdDto user = userServiceProxy.getUserId(xAuthToken);
        return commentService.modifyComment(user, commentId, reqCommentDto);
    }

    @DeleteMapping("/comments/{commentId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public Map<String, String> deleteComment(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @PathVariable Long commentId) {
        UserIdDto user = userServiceProxy.getUserId(xAuthToken);
        return commentService.deleteComment(user, commentId);
    }
}
