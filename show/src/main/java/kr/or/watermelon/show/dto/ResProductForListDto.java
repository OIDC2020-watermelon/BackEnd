package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.RRatedType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResProductForListDto {

    private Long id;

    private String title;

    private Category category;

    private Integer runningTime;

    private RRatedType rRated;

    private String thumbnailImgUrl;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private String place;

    private List<String> artists;

}