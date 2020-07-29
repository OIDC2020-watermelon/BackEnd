package kr.or.watermelon.ticket.reservation.repository;

import kr.or.watermelon.ticket.reservation.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByPerformanceId(Long performanceId);

    List<Ticket> findByReservationId(Long reservationId);

    void deleteByPerformanceId(Long performanceId);
}
