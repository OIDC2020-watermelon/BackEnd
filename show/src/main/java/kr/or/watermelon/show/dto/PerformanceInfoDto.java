package kr.or.watermelon.show.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceInfoDto {
    private String availableDate;
    private String startAt = LocalTime.parse("11:11:11").toString();
    private int duration = 120;
    private int session = 0;
    private Long productId;
    private int vipPrice = 20000;
    private int sPrice = 10000;
}
