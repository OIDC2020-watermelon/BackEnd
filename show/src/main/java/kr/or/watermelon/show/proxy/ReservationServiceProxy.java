package kr.or.watermelon.show.proxy;

import kr.or.watermelon.show.dto.PerformanceDto;
import kr.or.watermelon.show.dto.PerformanceInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "service-member", url="${service-member.ribbon.listOfServers}") // 유레카 없이 동작 없으면 자동으로 유레카에서 서비스명을 찾음
@FeignClient(name = "service-reservation")//, url = "http://localhost:8082")
public interface ReservationServiceProxy {
    @PostMapping(value = "/reservation/performance")
    PerformanceDto add(@RequestBody PerformanceInfoDto performanceInfo);

    @DeleteMapping("/reservation/performance/{productId}")
    void delete(@PathVariable Long productId);
}
