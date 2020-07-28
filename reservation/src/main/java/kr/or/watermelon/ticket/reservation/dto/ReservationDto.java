package kr.or.watermelon.ticket.reservation.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info {
        private String name;
        private LocalDate availableDate;
        private LocalTime startAt;
        private int pay;
        private Long userId;
        private Long[] ticketList;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private LocalDate availableDate;
        private LocalTime startAt;
        private String serialNumber;
        private boolean isCanceled;
        private LocalDate cancelableDate;
        private int pay;
        private LocalDateTime createdAt;
        private Long[] ticketList;
    }
}
