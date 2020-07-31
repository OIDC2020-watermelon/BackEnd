package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
