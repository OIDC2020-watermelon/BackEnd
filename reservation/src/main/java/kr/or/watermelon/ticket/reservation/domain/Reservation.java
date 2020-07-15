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
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate reservationDate;

    private String serialNumber;

    private boolean isCanceled;

    private LocalDate cancelableDate;

    private int pay;

    private Long userId;

    @OneToMany(mappedBy = "reservation")
    private List<Ticket> tickets = new ArrayList<>();
}
