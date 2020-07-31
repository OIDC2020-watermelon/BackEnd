package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductForListDto {

    private Long id;

    private String title;

    private String category;

    private Integer runningTime;

    private String rRated;

    private String thumbnailImgUrl;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private String place;

    private List<String> artists;

}