package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate availableDate;

    private LocalTime startAt;

    private int duration;

    private int session;

    private Long productId;

//    @Builder.Default
//    @OneToMany(mappedBy = "performance")
//    private List<Ticket> tickets = new ArrayList<>();
}
