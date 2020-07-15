package kr.or.watermelon.ticket.reservation.controller;

import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/api/ticket")
@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/{productId}")
    public List<Ticket> getTickets(@PathVariable Long productId) {
        return ticketService.getTickets(productId);
    }
}
