package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResArtistDto;
import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    public ResArtistDto getArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(NullPointerException::new);
        return modelMapper.map(artist, ResArtistDto.class);
    }

    public List<ResArtistDto> searchArtists(String keyword) {
        List<Artist> artists = artistRepository.findByNameContaining(keyword);
        return artists.stream()
                .map(p->modelMapper.map(p,ResArtistDto.class))
                .collect(Collectors.toList());
    }
}
