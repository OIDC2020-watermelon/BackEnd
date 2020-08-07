package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CommentFactory {

    private final CommentRepository commentRepository;
    private final Factory factory;

    public <T> List<Comment> saveItems(Function<T, CustomBuilder> func, List<T> args) {
        List<Comment> comments = (List<Comment>) factory.makeItems(func, args);
        return commentRepository.saveAll(comments);
    }

    public <T> Comment saveItem(Function<T, CustomBuilder> func, T arg) {
        Comment comment = (Comment) factory.makeItem(func, arg);
        return commentRepository.save(comment);
    }
}
