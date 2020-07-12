package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long>{
}
