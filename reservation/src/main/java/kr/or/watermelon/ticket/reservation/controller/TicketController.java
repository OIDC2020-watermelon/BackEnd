package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Ticket API"})
@CrossOrigin
@RequestMapping(value = "/api/ticket")
@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @ApiOperation(value="티켓 리스트", notes="해당 show에 맞는 티켓(좌석) 리스트를 조회합니다.")
    @GetMapping("/{showId}")
    public List<Ticket> getAll(@PathVariable Long showId) {
        return ticketService.getAll(showId);
    }
}
