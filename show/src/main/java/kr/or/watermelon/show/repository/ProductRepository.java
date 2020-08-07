package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(Category category, LocalDateTime now, LocalDateTime now1);

    List<Product> findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(LocalDateTime now, LocalDateTime now1);

    List<Product> findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, Category category, LocalDateTime now, LocalDateTime now1);

    List<Product> findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, LocalDateTime now, LocalDateTime now1);

    List<Product> findByNameContaining(String keyword);
}
