package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryExt {
}
