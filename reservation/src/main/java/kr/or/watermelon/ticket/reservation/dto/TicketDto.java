package kr.or.watermelon.ticket.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
}
