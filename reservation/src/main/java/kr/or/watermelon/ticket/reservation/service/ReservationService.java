package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Performance;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.dto.ReservationDto;
import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.dto.ReservationInfoDto;
import kr.or.watermelon.ticket.reservation.dto.UserIdDto;
import kr.or.watermelon.ticket.reservation.repository.PerformanceRepository;
import kr.or.watermelon.ticket.reservation.repository.ReservationRepository;
import kr.or.watermelon.ticket.reservation.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    public List<ReservationDto> getAll(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    public ReservationDto getOne(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Transactional
    public ReservationDto add(UserIdDto user, ReservationInfoDto reservationInfo) {
        String serialNumber = UUID.randomUUID().toString().replaceAll("-", "");

        Performance performance = performanceRepository.findById(reservationInfo.getPerformanceId()).orElse(null);

        List<Ticket> tickets = ticketRepository.findAllById(Arrays.asList(reservationInfo.getTicketList()));
        int pay = tickets.stream().mapToInt(Ticket::getPrice).sum();

        LocalDate cancelableDate = performance.getAvailableDate().minusDays(1);

        Reservation reservation = Reservation.builder()
                                    .name(reservationInfo.getName())
                                    .availableDate(performance.getAvailableDate())
                                    .startAt(performance.getStartAt())
                                    .serialNumber(serialNumber)
                                    .cancelableDate(cancelableDate)
                                    .pay(pay)
                                    .userId(user.getId())
                                    .pieces(reservationInfo.getTicketList().length)
                                    .build();

        Reservation newReservation = reservationRepository.save(reservation);

        tickets.forEach(ticket -> {
            ticket.setSold(true);
            ticket.setReservation(reservation);
        });

        ticketRepository.saveAll(tickets);

        return modelMapper.map(newReservation, ReservationDto.class);
    }

    public ReservationDto cancel(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        reservation.setCanceled(true);
        Reservation canceledReservation = reservationRepository.save(reservation);
        return modelMapper.map(canceledReservation, ReservationDto.class);
    }
}
