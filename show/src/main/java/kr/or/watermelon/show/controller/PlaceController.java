package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.dto.ResPlaceDto;
import kr.or.watermelon.show.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public ResPlaceDto getPlace(@PathVariable Long placeId){
        return placeService.getPlace(placeId);
    }
}
