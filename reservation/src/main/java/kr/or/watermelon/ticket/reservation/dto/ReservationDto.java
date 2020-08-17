package kr.or.watermelon.ticket.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate availableDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startAt;
    private String serialNumber;
    private boolean isCanceled;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate cancelableDate;
    private int pay;
    private int pieces;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private Long productId;
}
