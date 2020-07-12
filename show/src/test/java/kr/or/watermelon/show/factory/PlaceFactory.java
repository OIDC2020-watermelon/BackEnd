package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO 팩토리들 추상화해서 하나로 합쳐서 사용하기
@Component
@RequiredArgsConstructor
public class PlaceFactory {

    private final PlaceRepository placeRepository;

    public Place savePlace(String name) {
        Place yes24 = Place.builder().name(name).build();
        return placeRepository.save(yes24);
    }

    public List<Place> savePlaces(int count, String name) {
        List<Place> places = Stream.generate(() -> Place.builder().name(name).build())
                .limit(count)
                .collect(Collectors.toList());
        placeRepository.saveAll(places);
        return places;
    }
}
