package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    private String seatNumber;

    private int price;

    private boolean isSold;

    private String name;

    private LocalDate startAt;

    private LocalDate endAt;

    private LocalDate cancelableDate;

    private Long productId;

    @ManyToOne
    @JoinColumn(name="ID")
    private Reservation reservation;

    private void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
