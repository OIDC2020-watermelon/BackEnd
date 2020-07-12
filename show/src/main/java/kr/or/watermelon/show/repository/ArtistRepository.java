package kr.or.watermelon.show.repository;

import kr.or.watermelon.show.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long>{

    Page<Artist> findByNameContaining(String keyword, Pageable pageable);

}
