package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.ResPlaceDto;
import kr.or.watermelon.show.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"공연장API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{id}")
    @ApiOperation(value = "[공연장상세보기페이지(p34)]:공연장 상세정보 가져오기")
    public ResPlaceDto getPlace(@PathVariable Long id) {
        return placeService.getPlace(id);
    }

    @GetMapping("/search")
    public List<ResPlaceDto> searchPlaces(String keyword,
    @ApiOperation(value = "[공연검색페이지(p26)]:공연장 검색 리스트 가져오기")
                                          @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return placeService.searchPlaces(keyword, pageable);
    }
}
