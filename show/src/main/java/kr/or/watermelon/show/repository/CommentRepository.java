package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.CommentType;
import kr.or.watermelon.show.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByProductAndType(Product product, CommentType type, Pageable pageable);
}
