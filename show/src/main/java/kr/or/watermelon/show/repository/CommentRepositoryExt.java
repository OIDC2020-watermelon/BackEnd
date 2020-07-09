package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CommentRepositoryExt {

    Page<Comment> findAllByProductId(Long productId, Pageable pageable);

}
