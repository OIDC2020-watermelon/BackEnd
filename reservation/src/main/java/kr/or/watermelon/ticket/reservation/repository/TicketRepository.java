package kr.or.watermelon.ticket.reservation.repository;

import kr.or.watermelon.ticket.reservation.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "select ticket from Ticket ticket where ticket.product_id = :productId")
    List<Ticket> findAllByProductId(@Param("productId") Long productId);
}
