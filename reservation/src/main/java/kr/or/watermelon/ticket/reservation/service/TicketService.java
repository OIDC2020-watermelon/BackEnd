package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTickets(Long productId) {
        return ticketRepository.findAllByProductId(productId);
    }

    public void buyTickets(List<Ticket> ticketList) {
        ticketList.forEach(ticket -> ticket.setSold(true));
        ticketRepository.saveAll(ticketList);
    }
}
