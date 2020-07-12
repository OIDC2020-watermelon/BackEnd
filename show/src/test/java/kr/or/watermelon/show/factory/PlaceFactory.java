package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceFactory {

    private final PlaceRepository placeRepository;

    public Place savePlace(String name) {
        Place yes24 = Place.builder().name(name).build();
        return placeRepository.save(yes24);
    }
}
