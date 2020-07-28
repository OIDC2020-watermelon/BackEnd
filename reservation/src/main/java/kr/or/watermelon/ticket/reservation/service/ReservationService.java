package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.dto.ReservationDto;
import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationDto.Response> getAll(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.Response.class))
                .collect(Collectors.toList());
    }

    public ReservationDto.Response getOne(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return modelMapper.map(reservation, ReservationDto.Response.class);
    }

    @Transactional
    public ReservationDto.Response add(ReservationDto.Info reservationInfo) {
        String serialNumber = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDate cancelableDate = reservationInfo.getAvailableDate().minusDays(3);

        Reservation reservation = Reservation.builder()
                                    .name(reservationInfo.getName())
                                    .availableDate(reservationInfo.getAvailableDate())
                                    .startAt(reservationInfo.getStartAt())
                                    .serialNumber(serialNumber)
                                    .cancelableDate(cancelableDate)
                                    .pay(reservationInfo.getPay())
                                    .userId(reservationInfo.getUserId())
                                    .build();

        Reservation newReservation = reservationRepository.save(reservation);
        return modelMapper.map(newReservation, ReservationDto.Response.class);
    }

    public ReservationDto.Response cancel(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        reservation.setCanceled(true);
        Reservation canceledReservation = reservationRepository.save(reservation);
        return modelMapper.map(canceledReservation, ReservationDto.Response.class);
    }
}
