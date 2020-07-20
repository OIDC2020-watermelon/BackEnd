package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startAt;

    private LocalDate endAt;

    private int session;

    private Long productId;

    @Builder.Default
    @OneToMany(mappedBy = "performance")
    private List<Ticket> tickets = new ArrayList<>();
}
