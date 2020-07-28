package kr.or.watermelon.ticket.reservation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.ticket.reservation.domain.Performance;
import kr.or.watermelon.ticket.reservation.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Performance API"})
@CrossOrigin
@RequestMapping(value = "/api/performance")
@RestController
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    @ApiOperation(value="공연 리스트", notes = "상품 별 공연 리스트를 조회합니다.")
    @GetMapping("/{productId}")
    public List<Performance> getList(@PathVariable Long productId) {
        return performanceService.getList(productId);
    }


}
