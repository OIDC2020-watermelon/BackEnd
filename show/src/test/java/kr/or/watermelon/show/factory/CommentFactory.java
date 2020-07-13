package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentFactory {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ProductFactory productFactory;

    public List<Comment> saveComments(Function<String, Comment> contentCommentMaker, List<String> contents) {
        List<Comment> comments = contents.stream()
                .map(contentCommentMaker)
                .collect(Collectors.toList());

        commentRepository.saveAll(comments);
        Collections.reverse(comments);
        return comments;
    }
}
