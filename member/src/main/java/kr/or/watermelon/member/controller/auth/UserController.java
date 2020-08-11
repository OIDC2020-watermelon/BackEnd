package kr.or.watermelon.member.controller.auth;

import kr.or.watermelon.member.dto.UserIdDto;
import kr.or.watermelon.member.dto.UserDto;
import kr.or.watermelon.member.model.response.CommonResult;
import kr.or.watermelon.member.model.response.SingleResult;
import kr.or.watermelon.member.service.ResponseService;
import io.swagger.annotations.*;
import kr.or.watermelon.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "인증받은 사용자의 회원 정보를 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<UserDto> getUser() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userService.getUser(email));
    }
    @ApiOperation(value = "회원 아이디 단건 조회 (다른 서비스들 호출)", notes = "인증받은 사용자의 아이디를 조회한다")
    @GetMapping(value = "/userId")
    public UserIdDto getUserId() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return userService.getUserId(email);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "인증받은 사용자의 회원 정보 중 하나의 정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<UserDto> modify(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(userService.modify(email, userDto));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제 (회원 탈퇴)", notes = "인증받은 사용자의 회원 정보를 삭제한다")
    @DeleteMapping(value = "/user")
    public CommonResult delete() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.delete(authentication.getName());
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}