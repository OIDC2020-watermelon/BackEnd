package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResArtistDto;
import kr.or.watermelon.show.dto.ResArtistForListDto;
import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<ResArtistForListDto> searchArtists(String keyword, Pageable pageable) {
        Page<Artist> artists = artistRepository.findByNameContaining(keyword, pageable);
        return artists.stream()
                .map(p -> modelMapper.map(p, ResArtistForListDto.class))
                .collect(Collectors.toList());
    }
}
