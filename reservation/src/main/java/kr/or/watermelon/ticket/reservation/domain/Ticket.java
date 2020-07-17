package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    private String name;

    private String seatNumber;

    private Grade grade;

    private int row;

    private int column;

    private int price;

    @Setter
    @ColumnDefault("0")
    private boolean isSold;

    @ManyToOne
    @JoinColumn(name="RESERVATION_ID")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="SHOW_ID")
    private Show show;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setShow(Show show) {
        this.show = show;
    }
}

