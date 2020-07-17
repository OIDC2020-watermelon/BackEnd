package kr.or.watermelon.ticket.reservation.controller;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.service.ReservationService;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/api/reservation")
@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TicketService ticketService;

    // 예매 하기
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@RequestBody Reservation reservation,
                               @RequestBody List<Ticket> ticketList,
                               @RequestBody Long userId) {
        LocalDate availableDate = reservation.getAvailableDate();
        LocalTime availableTime = reservation.getAvailableTime();
        int pay = reservation.getPay();
        String name = reservation.getName();

        Reservation newReservation = reservationService.addReservation(availableDate, availableTime, pay, userId, name);
        ticketService.buyTickets(ticketList, newReservation);
    }

    // 예매 리스트
    @GetMapping("/{userId}")
    public Page<Reservation> getAll(@PathVariable Long userId) {
        return reservationService.getAll(userId);
    }

    // 예매 상세
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long reservationId) {
        return reservationService.getReservation(reservationId);
    }

    // 예매 취소
    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.cancelReservation(reservationId);
        List<Ticket> tickets = reservation.getTickets();

    }
}
