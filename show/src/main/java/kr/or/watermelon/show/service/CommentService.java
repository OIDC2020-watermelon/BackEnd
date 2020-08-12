package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.CommentDto;
import kr.or.watermelon.show.dto.ReqCommentDto;
import kr.or.watermelon.show.dto.UserIdDto;
import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.CommentType;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public List<CommentDto> getCommentsByProductId(Long productId, CommentType type, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByProductAndType(Product.builder().id(productId).build(), type, pageable);
        List<CommentDto> commentResponses = comments.stream()
                .map(c -> modelMapper.map(c, CommentDto.class))
                .collect(Collectors.toList());
        return commentResponses;
    }

    public CommentDto registerComment(Long productId, UserIdDto user, CommentType type, ReqCommentDto reqCommentDto) {
        Comment comment = Comment.builder()
                .product(Product.builder().id(productId).build())
                .userId(user.getId())
                .type(type)
                .content(reqCommentDto.getContent())
                .createdDateTime(reqCommentDto.getCreatedDateTime())
                .build();
        Comment newComment = commentRepository.save(comment);
        return modelMapper.map(newComment, CommentDto.class);
    }

    public CommentDto modifyComment(UserIdDto user, Long commentId, ReqCommentDto reqCommentDto) {
        Comment comment = commentRepository.getOne(commentId);
        if(!comment.getUserId().equals(user.getId())) { // 인증받은 댓글 수정 요청한 사용자와 댓글 작성자가 같은지 확인
            throw new IllegalArgumentException("The current user cannot modify this commment");
        }
        comment.setUpdate(reqCommentDto.getContent(), reqCommentDto.getModifiedDateTime());
        Comment modifiedComment = commentRepository.save(comment);
        return modelMapper.map(modifiedComment, CommentDto.class);
    }

    public Map<String, String> deleteComment(UserIdDto user, Long commentId) {
        Comment comment = commentRepository.getOne(commentId);
        if(!comment.getUserId().equals(user.getId())) { // 인증받은 댓글 수정 요청한 사용자와 댓글 작성자가 같은지 확인
            throw new IllegalArgumentException("The current user cannot delete this commment");
        }
        Map<String, String> response = new HashMap<String, String>();
        commentRepository.deleteById(commentId);
        response.put("result", "200");
        response.put("msg", "ok");
        return response;
    }
}
