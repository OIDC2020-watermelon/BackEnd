package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlaceFactory {

    private final PlaceRepository placeRepository;
    private final Factory factory;

    public <T> List<Place> saveItems(Function<T, CustomBuilder> func, List<T> args) {
        List<Place> places = (List<Place>) factory.makeItems(func, args);
        return placeRepository.saveAll(places);
    }

    public <T> Place saveItem(Function<T, CustomBuilder> func, T arg) {
        Place place = (Place) factory.makeItem(func, arg);
        return placeRepository.save(place);
    }
}
