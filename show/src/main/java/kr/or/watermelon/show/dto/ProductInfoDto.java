package kr.or.watermelon.show.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.RRatedType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
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
}
