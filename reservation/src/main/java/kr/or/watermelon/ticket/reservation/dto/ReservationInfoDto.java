package kr.or.watermelon.ticket.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationInfoDto {
    private String name;
    private Long performanceId;
    private Long[] ticketList;
}
