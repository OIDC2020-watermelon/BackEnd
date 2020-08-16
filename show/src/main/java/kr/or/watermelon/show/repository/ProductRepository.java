package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(Category category, LocalDateTime now, LocalDateTime now1);

    List<Product> findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(LocalDateTime now, LocalDateTime now1);

    List<Product> findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, Category category, LocalDateTime now, LocalDateTime now1);

    List<Product> findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(String keyword, LocalDateTime now, LocalDateTime now1);

    List<Product> findByTitleContaining(String keyword);

    Page<Product> findAllByReleaseEndTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    Optional<Product> findBySerial(UUID serial);

}
