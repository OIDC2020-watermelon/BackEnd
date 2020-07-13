package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Reservation> getAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional 
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservation.setCanceled(true);

        reservationRepository.save(reservation);
    }
}
