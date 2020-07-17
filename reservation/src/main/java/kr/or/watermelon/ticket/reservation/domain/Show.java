package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Show {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate startAt;

    private LocalDate endAt;

    private int session;

    private Long productId;

    @OneToMany(mappedBy = "ticket")
    private List<Ticket> tickets = new ArrayList<>();
}
