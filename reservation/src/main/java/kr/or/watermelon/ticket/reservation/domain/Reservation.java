package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalDate availableDate;

    private LocalTime availableTime;

    private String serialNumber;

    @ColumnDefault("false")
    private boolean isCanceled;

    private LocalDate cancelableDate;

    private int pay;

    private Long userId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="SHOW_ID")
    private Show show;

    @OneToMany(mappedBy = "ticket")
    private List<Ticket> tickets = new ArrayList<>();
}
