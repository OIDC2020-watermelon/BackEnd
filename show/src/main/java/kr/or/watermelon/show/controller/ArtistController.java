package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.dto.ResArtistDto;
import kr.or.watermelon.show.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{artistId}")
    public ResArtistDto getArtist(@PathVariable Long artistId) {
        return artistService.getArtist(artistId);
    }

    @GetMapping("/search")
    public List<ResArtistDto> searchArtists(String keyword,
                                            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return artistService.searchArtists(keyword, pageable);
    }

}
