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

    public <T> List<Comment> saveComments(Function<T, Comment> f, List<T> ts) {
        List<Comment> comments = ts.stream()
                .map(f)
                .collect(Collectors.toList());

        commentRepository.saveAll(comments);
        Collections.reverse(comments);
        return comments;
    }
}
