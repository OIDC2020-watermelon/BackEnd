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
    enum Grade {
        VIP, S,
    }

    @Id
    @GeneratedValue
    private Long id;

    private String seatNumber;

    private Grade grade;

    private int row;

    private int column;

    private int price;

    @Setter
    private boolean isSold;

    @ManyToOne
    @JoinColumn(name="TICKET_ID")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="SHOW_ID")
    private Show show;

    private void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    private void setShow(Show show) {
        this.show = show;
    }
}

