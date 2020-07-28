package kr.or.watermelon.ticket.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class PerformanceDto {
    private Long id;
    private LocalDate availableDate;
    private LocalTime startAt;
    private int duration;
    private int session;
    private Long productId;
}
