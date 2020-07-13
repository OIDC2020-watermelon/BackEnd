package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByTitleContainingAndCategory(String keyword, Category category, Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByTitleContaining(String keyword, Pageable pageable);

    Page<Product> findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(Category category, LocalDateTime now, LocalDateTime now1, Pageable pageable);

    Page<Product> findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(LocalDateTime now, LocalDateTime now1, Pageable pageable);

    Page<Product> findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, Category category, LocalDateTime now, LocalDateTime now1, Pageable pageable);

    Page<Product> findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, LocalDateTime now, LocalDateTime now1, Pageable pageable);

    Page<Product> findByTitleContainingAndReleaseEndTimeBefore(String keyword, LocalDateTime now, Pageable pageable);

    Page<Product> findByTitleContainingAndReleaseStartTimeAfter(String keyword, LocalDateTime now, Pageable pageable);

    Page<Product> findByCategoryAndReleaseEndTimeBefore(Category category, LocalDateTime now, Pageable pageable);

    Page<Product> findByCategoryAndReleaseStartTimeAfter(Category category, LocalDateTime now, Pageable pageable);

    Page<Product> findByReleaseStartTimeAfter(LocalDateTime now, Pageable pageable);

    Page<Product> findByReleaseEndTimeBefore(LocalDateTime now, Pageable pageable);

    Page<Product> findByTitleContainingAndCategoryAndReleaseEndTimeBefore(String keyword, Category category, LocalDateTime now, Pageable pageable);

    Page<Product> findByTitleContainingAndCategoryAndReleaseStartTimeAfter(String keyword, Category category, LocalDateTime now, Pageable pageable);

}
