package kr.or.watermelon.show.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.or.watermelon.show.entity.Artist;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Place;
import kr.or.watermelon.show.entity.RRatedType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductInfoDto {

    private String title;

    private Category category;

    private Integer runningTime;

    private RRatedType rRated;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private Long placeId;

    private List<Long> artistIds;

    private List<LocalDate> availableDates;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startAt;

    private int duration;

    private int session;

    private int vipPrice;

    private int sPrice;

    public List<Artist> makeNewArtists() {
        return artistIds.stream()
                .map(id -> Artist.builder().id(id).build())
                .collect(Collectors.toList());
    }

    public Place makeNewPlace() {
        return Place.builder().id(placeId).build();
    }
}
