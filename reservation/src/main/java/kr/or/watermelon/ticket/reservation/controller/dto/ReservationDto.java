package kr.or.watermelon.ticket.reservation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private String name;
    private LocalDate availableDate;
    private LocalTime availableTime;
    private int pay;
}
