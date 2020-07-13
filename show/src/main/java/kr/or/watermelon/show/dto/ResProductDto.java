package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResProductDto {

    private String title;

    private Category category;

    private ResPlaceDto place;

    private String imgUrl;

    private String thumbnailImgUrl;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private LocalDateTime startShowTime;

    private LocalDateTime endShowTime;


}