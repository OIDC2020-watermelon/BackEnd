package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.controller.dto.ReservationDto;
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

    public Reservation getOne(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Transactional
    public Reservation add(ReservationDto reservationDto, Long userId) {
        String serialNumber = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDate cancelableDate = reservationDto.getAvailableDate().minusDays(3);

        Reservation reservation = Reservation.builder()
                                    .name(reservationDto.getName())
                                    .availableDate(reservationDto.getAvailableDate())
                                    .availableTime(reservationDto.getAvailableTime())
                                    .serialNumber(serialNumber)
                                    .cancelableDate(cancelableDate)
                                    .pay(reservationDto.getPay())
                                    .userId(userId)
                                    .build();

        return reservationRepository.save(reservation);
    }

    public Reservation cancel(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        reservation.setCanceled(true);

        Reservation canceledReservation = reservationRepository.save(reservation);
        return canceledReservation;
    }
}
