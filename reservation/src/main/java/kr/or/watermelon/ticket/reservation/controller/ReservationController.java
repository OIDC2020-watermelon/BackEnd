package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.ticket.reservation.controller.dto.ReservationDto;
import kr.or.watermelon.ticket.reservation.domain.Reservation;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.service.ReservationService;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = {"Reservation API"})
@CrossOrigin
@RequestMapping(value = "/api/reservation")
@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TicketService ticketService;

    // 예매 하기
    @ApiOperation(value="예매하기", notes="예매를 진행합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation add(@RequestBody ReservationDto reservationDto,
                               @RequestBody List<Ticket> ticketList,
                               @RequestBody Long userId) {
        Reservation newReservation = reservationService.add(reservationDto, userId);
        ticketService.buy(ticketList, newReservation);
        return newReservation;
    }

    // 예매 리스트
    @ApiOperation(value="예매 리스트", notes="사용자가 예매한 목록을 조회합니다.")
    @GetMapping("/{userId}")
    public List<Reservation> getList(@PathVariable Long userId) {
        return reservationService.getAll(userId);
    }

    // 예매 상세
    @ApiOperation(value="예매 상세", notes="예매 상세")
    @GetMapping("/{id}")
    public Reservation getOne(@PathVariable Long reservationId) {
        return reservationService.getOne(reservationId);
    }

    // 예매 취소
    @ApiOperation(value="예매 취소", notes="예매를 취소합니다.")
    @DeleteMapping("/{id}")
    public Reservation cancel(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.cancel(reservationId);

        List<Ticket> tickets = reservation.getTickets();
        ticketService.cancel(tickets);

        return reservation;
    }
}
