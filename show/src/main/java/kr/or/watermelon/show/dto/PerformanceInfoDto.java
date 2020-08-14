package kr.or.watermelon.show.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceInfoDto {
    private String availableDate;
    private String startAt;
    private int duration;
    private int session;
    private Long productId;
    private int vipPrice;
    private int sPrice;
}
