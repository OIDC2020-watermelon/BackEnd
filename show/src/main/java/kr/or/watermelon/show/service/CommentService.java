package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResCommentDto;
import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

        Page<Comment> comments = commentRepository.findAllByProductId(productId, pageable);
    public List<ResCommentDto> getCommentsByProductId(Long productId, Pageable pageable) {
        List<ResCommentDto> commentResponses = comments.stream()
                .map(c -> modelMapper.map(c, ResCommentDto.class))
                .collect(Collectors.toList());
        return commentResponses;
    }
}
