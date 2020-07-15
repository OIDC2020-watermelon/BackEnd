package kr.or.watermelon.ticket.reservation.repository;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByUserId(Long userId);
}
