package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    public enum Grade {
        VIP, S,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Grade grade;

    private int rowNum;

    private int colNum;

    private int price;

    @ColumnDefault("0")
    private boolean isSold;

    @ManyToOne
    @JoinColumn(name="reservation_id")

    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="performance_id")
    private Performance performance;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}

