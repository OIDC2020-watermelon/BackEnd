package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceFactory {

    private final PlaceRepository placeRepository;

    public Place savePlace() {
        Place yes24 = Place.builder().name("yes24").build();
        return placeRepository.save(yes24);
    }
}
