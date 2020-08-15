package kr.or.watermelon.ticket.reservation.proxy;

import kr.or.watermelon.ticket.reservation.dto.UserIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "service-member")
public interface UserServiceProxy {
    @GetMapping(value = "/auth/userId")
    public UserIdDto getUserId(@RequestHeader("X-AUTH-TOKEN") String xAuthToken);
}
