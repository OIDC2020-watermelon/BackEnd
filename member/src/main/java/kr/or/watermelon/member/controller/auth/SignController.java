package kr.or.watermelon.member.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.or.watermelon.member.config.security.JwtTokenProvider;
import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.model.response.SingleResult;
import kr.or.watermelon.member.service.ResponseService;
import kr.or.watermelon.member.service.UserService;
import kr.or.watermelon.member.service.SocialSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class SignController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final SocialSignService socialSignService;

    @ApiOperation(value = "소셜 계정 로그인 / 소셜 계정 회원가입", notes = "가입된 회원의 경우 소셜 계정으로 로그인을 한다 / 그렇지 않은 경우 소셜 계정으로 회원가입을 한다.")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> loginProviderNaver(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "naver") @PathVariable String provider,
                                              @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
        User signedUser = null;
        switch (provider) {
            case "naver":
                signedUser = socialSignService.signupByNaver(provider, accessToken);
                break;
            case "kakao":
                signedUser = socialSignService.signupByKakao(provider, accessToken);
                break;
        }
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(signedUser.getUid()), signedUser.getRoles()));
    }

    /*
        @ApiOperation(value = "로그인", notes = "회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID: 이메일", required = true) @RequestParam String email,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = userJpaRepo.findByUid(email).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public SingleResult<SignupUserDto> signup(@RequestBody SignupUserDto signupUserDto) {
        return responseService.getSingleResult(userService.signup(signupUserDto));
    }
     */
}