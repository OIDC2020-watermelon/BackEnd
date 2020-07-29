package kr.or.watermelon.ticket.reservation.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceDto {
    private Long id;
    private LocalDate availableDate;
    private LocalTime startAt;
    private int duration;
    private int session;
    private Long productId;
}
