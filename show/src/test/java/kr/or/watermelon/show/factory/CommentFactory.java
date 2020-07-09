package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CommentFactory {
    @Autowired
    CommentRepository commentRepository;

    public Comment saveComment(Long productId, String content) {
        Comment newComment = Comment.builder()
                .productId(productId)
                .content(content).build();

        commentRepository.save(newComment);
        return newComment;
    }

    public List<Comment> saveRandomComments(int count, Long productId) {
        RandomString randomString = new RandomString();
        List<Comment> comments = Stream.generate(() -> Comment.builder()
                .productId(productId)
                .content(randomString.nextString())
                .build())
                .limit(count)
                .collect(Collectors.toList());

        commentRepository.saveAll(comments);
        Collections.reverse(comments);
        return comments;
    }
}
