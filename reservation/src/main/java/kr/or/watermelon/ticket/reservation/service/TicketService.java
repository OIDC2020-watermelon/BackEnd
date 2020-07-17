package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAll(Long showId) {
        return ticketRepository.findAllByShowId(showId);
    }

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
}
