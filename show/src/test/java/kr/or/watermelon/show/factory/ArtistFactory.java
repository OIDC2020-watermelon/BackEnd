package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistFactory {

    private final ArtistRepository artistRepository;

    public Artist saveArtist(String name) {
        Artist tyga = Artist.builder().name(name).build();
        return artistRepository.save(tyga);
    }
}
