package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.PlaceDto;
import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ModelMapper modelMapper;

    public PlaceDto getPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(NullPointerException::new);
        return modelMapper.map(place, PlaceDto.class);
    }

    public List<PlaceDto> searchPlaces(String keyword) {
        List<Place> places = placeRepository.findByNameContaining(keyword);
        return places.stream()
                .map(p -> modelMapper.map(p, PlaceDto.class))
                .collect(Collectors.toList());
    }
}
