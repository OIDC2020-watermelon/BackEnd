package kr.or.watermelon.show.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import kr.or.watermelon.show.entity.Comment;
import kr.or.watermelon.show.entity.QComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CommentRepositoryExtImpl extends QuerydslRepositorySupport implements CommentRepositoryExt {

    public CommentRepositoryExtImpl() {
        super(Comment.class);
    }

    @Override
    public Page<Comment> findAllByProductId(Long productId, Pageable pageable) {
        QComment comment = QComment.comment;
        JPQLQuery<Comment> query = from(comment).where(comment.productId.eq(productId));
        JPQLQuery<Comment> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Comment> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
