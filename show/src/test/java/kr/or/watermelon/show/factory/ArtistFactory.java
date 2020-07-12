package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistFactory {

    private final ArtistRepository artistRepository;

    public Artist saveArtist() {
        Artist tyga = Artist.builder().name("tyga").build();
        return artistRepository.save(tyga);
    }
}
