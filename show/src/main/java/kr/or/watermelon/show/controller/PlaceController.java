package kr.or.watermelon.show.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public ResPlaceDto getPlace(@PathVariable Long placeId){
        return placeService.getPlace(placeId);
    }

    @GetMapping("/search")
    public List<ResPlaceDto> searchPlaces(String keyword,
                                          @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){
        return placeService.searchPlaces(keyword,pageable);
    }
}
