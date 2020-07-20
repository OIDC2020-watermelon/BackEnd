package kr.or.watermelon.ticket.reservation.repository;

import kr.or.watermelon.ticket.reservation.domain.Performance;

import java.util.List;

public interface PerformanceRepository {
    List<Performance> findByProductId(Long productId);
}
