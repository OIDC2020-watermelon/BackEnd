package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Place;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductInfoDto {

    private String title;

    private Category category;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private Long placeId;

    private List<Long> artistIds;

    private List<LocalDate> availableDates;

    public List<Artist> makeNewArtists() {
        return artistIds.stream()
                .map(id -> Artist.builder().id(id).build())
                .collect(Collectors.toList());
    }

    public Place makeNewPlace() {
        return Place.builder().id(placeId).build();
    }
}
