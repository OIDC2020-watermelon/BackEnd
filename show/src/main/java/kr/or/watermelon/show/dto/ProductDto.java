package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDto {

    private Long id;

    private String title;

    private String category;

    private Integer runningTime;

    private String rRated;

    private String imgUrl;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private PlaceDto place;

    private List<ArtistForListDto> artists;


}