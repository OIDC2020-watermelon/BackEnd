package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ArtistFactory {

    private final ArtistRepository artistRepository;

    public Artist saveArtist(String name) {
        Artist tyga = Artist.builder().name(name).build();
        return artistRepository.save(tyga);
    }

    public List<Artist> saveArtists(int count, String name) {
        List<Artist> artists = Stream.generate(() -> Artist.builder().name(name).build())
                .limit(count)
                .collect(Collectors.toList());
        artistRepository.saveAll(artists);
        return artists;
    }
}
