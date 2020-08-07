package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ArtistDto;
import kr.or.watermelon.show.dto.ArtistForListDto;
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

    public ArtistDto getArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(NullPointerException::new);
        return modelMapper.map(artist, ArtistDto.class);
    }

    public List<ArtistForListDto> searchArtists(String keyword) {
        List<Artist> artists = artistRepository.findByNameContaining(keyword);
        return artists.stream()
                .map(p -> modelMapper.map(p, ArtistForListDto.class))
                .collect(Collectors.toList());
    }
}
