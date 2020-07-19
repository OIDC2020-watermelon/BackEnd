package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.ResArtistDto;
import kr.or.watermelon.show.dto.ResArtistForListDto;
import kr.or.watermelon.show.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"아티스트API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{id}")
    @ApiOperation(value = "[아티스트상세보기페이지(p33)]:아티스트 상세정보 가져오기")
    public ResArtistDto getArtist(@PathVariable Long id) {
        return artistService.getArtist(id);
    }

    @GetMapping("/search")
    @ApiOperation(value = "[아티스트검색페이지(p25)]:아티스트 검색 리스트 가져오기")
    public List<ResArtistForListDto> searchArtists(@RequestParam(defaultValue = "") String keyword,
                                                   @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return artistService.searchArtists(keyword, pageable);
    }
}
