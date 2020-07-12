package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResArtistDto;
import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    public ResArtistDto getArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(NullPointerException::new);
        return modelMapper.map(artist, ResArtistDto.class);
    }
}
