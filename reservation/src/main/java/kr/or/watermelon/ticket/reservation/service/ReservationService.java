package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Reservation> getAll(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addReservation(LocalDate availableDate, LocalTime availableTime,
                               int pay, Long userId) {
        String serialNumber = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDate cancelableDate = availableDate.minusDays(3);

        Reservation reservation = Reservation.builder()
                                    .availableDate(availableDate)
                                    .availableTime(availableTime)
                                    .serialNumber(serialNumber)
                                    .cancelableDate(cancelableDate)
                                    .pay(pay)
                                    .userId(userId)
                                    .build();

        reservationRepository.save(reservation);
    }
}
