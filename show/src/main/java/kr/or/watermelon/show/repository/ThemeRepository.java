package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.entity.ThemeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Page<Theme> findByType(ThemeType type, Pageable pageable);
}
