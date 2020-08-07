package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByNameContaining(String keyword);

}
