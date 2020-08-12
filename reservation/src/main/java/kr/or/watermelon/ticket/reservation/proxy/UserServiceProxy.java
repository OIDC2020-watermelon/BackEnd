package kr.or.watermelon.ticket.reservation.proxy;

import kr.or.watermelon.ticket.reservation.dto.UserIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

//@FeignClient(name = "service-member", url="${service-member.ribbon.listOfServers}") // 유레카 없이 동작할때 url을 매핑. 없으면 자동으로 유레카에서 서비스명을 찾음
@FeignClient(name = "service-member")
public interface UserServiceProxy {
    @GetMapping(value = "/auth/userId")
    public UserIdDto getUserId(@RequestHeader("X-AUTH-TOKEN") String xAuthToken);
}
