package kr.or.watermelon.ticket.reservation.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate availableDate;

    private LocalTime startAt;

    private String serialNumber;

    @ColumnDefault("0")
    private boolean isCanceled;

    private LocalDate cancelableDate;

    private int pay;

    private int pieces;

    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @Builder.Default
//    @OneToMany(mappedBy = "reservation")
//    private List<Ticket> tickets = new ArrayList<>();
}
