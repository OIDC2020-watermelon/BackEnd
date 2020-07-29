package kr.or.watermelon.ticket.reservation.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    enum Grade {
        VIP, S,
    }

    private Long id;
    private String seatNumber;
    private Grade grade;
    private int rowNum;
    private int colNum;
    private int price;
    private boolean isSold;
    private Long performanceId;
    private Long reservationId;
}
