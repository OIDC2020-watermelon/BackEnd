package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.ticket.reservation.dto.ReservationDto;
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

    @ApiOperation(value="예매하기", notes="예매를 진행합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto.Response add(@RequestBody ReservationDto.Info reservationInfo) {
        ReservationDto.Response newReservation = reservationService.add(reservationInfo);
        //ticketService.buy(reservationInfo.getTicketList(), newReservation.getId());
        return newReservation;
    }

    @ApiOperation(value="예매 리스트", notes="사용자가 예매한 목록을 조회합니다.")
    @GetMapping("/user/{userId}")
    public List<ReservationDto.Response> getList(@PathVariable Long userId) {
        return reservationService.getAll(userId);
    }

    @ApiOperation(value="예매 상세", notes="예매 상세")
    @GetMapping("/{reservationId}")
    public ReservationDto.Response getOne(@PathVariable Long reservationId) {
        return reservationService.getOne(reservationId);
    }

    @ApiOperation(value="예매 취소", notes="예매를 취소합니다.")
    @DeleteMapping("/{reservationId}")
    public ReservationDto.Response cancel(@PathVariable Long reservationId) {
        ReservationDto.Response reservationDto = reservationService.cancel(reservationId);
        ticketService.cancel(reservationId);

        return reservationDto;
    }
}
