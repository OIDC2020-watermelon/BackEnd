package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.ticket.reservation.dto.ReservationDto;
import kr.or.watermelon.ticket.reservation.dto.ReservationInfoDto;
import kr.or.watermelon.ticket.reservation.dto.UserIdDto;
import kr.or.watermelon.ticket.reservation.proxy.UserServiceProxy;
import kr.or.watermelon.ticket.reservation.service.ReservationService;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Reservation API"})
@CrossOrigin
@RequestMapping(value = "/reservation")
@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserServiceProxy userServiceProxy;

    @ApiOperation(value="예매하기", notes="예매를 진행합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public ReservationDto add(@RequestHeader("X-AUTH-TOKEN") String xAuthToken, @RequestBody ReservationInfoDto reservationInfo) {
        UserIdDto user = userServiceProxy.getUserId(xAuthToken);
        ReservationDto reservationDto = reservationService.add(user, reservationInfo);

        return reservationDto;
    }

    @ApiOperation(value="예매 리스트", notes="사용자가 예매한 목록을 조회합니다.")
    @GetMapping("/user/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    public List<ReservationDto> getList(@RequestHeader("X-AUTH-TOKEN") String xAuthToken) {
        UserIdDto user = userServiceProxy.getUserId(xAuthToken);
        return reservationService.getAll(user.getId());
    }

    @ApiOperation(value="예매 상세", notes="예매 상세")
    @GetMapping("/{reservationId}")
    public ReservationDto getOne(@PathVariable Long reservationId) {
        return reservationService.getOne(reservationId);
    }

    @ApiOperation(value="예매 취소", notes="예매를 취소합니다.")
    @DeleteMapping("/{reservationId}")
    public ReservationDto cancel(@PathVariable Long reservationId) {
        ReservationDto reservationDto = reservationService.cancel(reservationId);
        ticketService.cancel(reservationId);

        return reservationDto;
    }
}
