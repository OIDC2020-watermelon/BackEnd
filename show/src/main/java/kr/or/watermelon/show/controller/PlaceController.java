package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.PlaceDto;
import kr.or.watermelon.show.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"공연장API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{id}")
    @ApiOperation(value = "[공연장상세보기페이지(p34)]:공연장 상세정보 가져오기")
    public PlaceDto getPlace(@PathVariable Long id) {
        return placeService.getPlace(id);
    }

    @GetMapping("/search")
    @ApiOperation(value = "[공연검색페이지(p26)]:공연장 검색 리스트 가져오기")
    public List<PlaceDto> searchPlaces(@RequestParam(defaultValue = "") String keyword) {
        return placeService.searchPlaces(keyword);
    }
}
