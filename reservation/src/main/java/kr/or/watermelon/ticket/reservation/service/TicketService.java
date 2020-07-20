package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void buy(List<Ticket> ticketList, Reservation reservation) {
        ticketList.forEach(ticket -> {
            ticket.setSold(true);
            ticket.setReservation(reservation);
        });
        ticketRepository.saveAll(ticketList);
    }

    public List<Ticket> cancel(List<Ticket> ticketList) {
        ticketList.forEach(ticket -> ticket.setSold(false));
        List<Ticket> canceledTickets = ticketRepository.saveAll(ticketList);

        return canceledTickets;
    }

    public List<Ticket> getListByPerformance(Long performanceId) {
        return ticketRepository.findAllByPerformanceId(performanceId);
    }

    public List<Ticket> getListByReservation(Long reservationId) {
        return ticketRepository.findAllByRerservationId(reservationId);
    }
}
