package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurtProductDto {

    private Long id;

    private String title;

    private String thumbnailImgUrl;

    private LocalDateTime releaseStartTime;

    private LocalDateTime releaseEndTime;

    private String place;

}