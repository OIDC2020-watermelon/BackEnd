package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ArtistFactory {

    private final ArtistRepository artistRepository;
    private final Factory factory;

    public <T> List<Artist> saveArtists(Function<T, CustomBuilder> func, List<T> args) {
        List<Artist> artists = (List<Artist>) factory.makeItems(func, args);
        return artistRepository.saveAll(artists);
    }

    public <T> Artist saveItem(Function<T, CustomBuilder> func, T arg) {
        Artist artist = (Artist) factory.makeItem(func, arg);
        return artistRepository.save(artist);
    }
}
