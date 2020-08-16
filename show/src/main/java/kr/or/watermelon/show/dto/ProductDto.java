package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDto {

    private Long id;

    private UUID serial;

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

    private List<ArtistForProductDto> artists;


}