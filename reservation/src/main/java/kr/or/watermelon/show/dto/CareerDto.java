package kr.or.watermelon.show.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CareerDto {

    private LocalDateTime date;

    private String description;

}
