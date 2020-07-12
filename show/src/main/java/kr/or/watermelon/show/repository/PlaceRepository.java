package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {

}
