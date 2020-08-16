package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.or.watermelon.ticket.reservation.dto.PerformanceDto;
import kr.or.watermelon.ticket.reservation.dto.PerformanceInfoDto;
import kr.or.watermelon.ticket.reservation.service.PerformanceService;
import kr.or.watermelon.ticket.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Performance API"})
@CrossOrigin
@RequestMapping(value = "/reservation/performance")
@RestController
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private TicketService ticketService;

    @ApiOperation(value="공연 리스트", notes = "상품 별 공연 리스트를 조회합니다.")
    @GetMapping("/{productId}")
    public List<PerformanceDto> getList(@PathVariable Long productId) {
        return performanceService.getList(productId);
    }

    @ApiOperation(value = "공연 생성", notes = "공연을 생성하고 해당 공연의 티켓(좌석)을 생성합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PerformanceDto add(@ApiParam(name = "message", value = "Message to send", required = true)
            @RequestBody PerformanceInfoDto performanceInfo) {
        PerformanceDto performanceDto = performanceService.add(performanceInfo);

        return performanceDto;
    }

    @ApiOperation(value = "공연 삭제", notes = "공연을 삭제하고 해당 공연의 티켓(좌석)을 삭제합니다.")
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        performanceService.delete(productId);
    }
}
