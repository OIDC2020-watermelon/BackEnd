package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.RRatedType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResProductDto {

    private Long id;

    private String title;

    private Category category;

    private Integer runningTime;

    private RRatedType rRated;

    private String imgUrl;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private ResPlaceDto place;

    private List<ResArtistDto> artists;


}