package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.CommentDto;
import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.Product;
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

    public List<CommentDto> getCommentsByProductId(Long productId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByProduct(Product.builder().id(productId).build(), pageable);
        List<CommentDto> commentResponses = comments.stream()
                .map(c -> modelMapper.map(c, CommentDto.class))
                .collect(Collectors.toList());
        return commentResponses;
    }
}
