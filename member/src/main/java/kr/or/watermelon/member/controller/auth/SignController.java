package kr.or.watermelon.member.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.or.watermelon.member.advice.exception.CEmailSigninFailedException;
import kr.or.watermelon.member.advice.exception.CUserNotFoundException;
import kr.or.watermelon.member.config.security.JwtTokenProvider;
import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.model.response.CommonResult;
import kr.or.watermelon.member.model.response.SingleResult;
import kr.or.watermelon.member.model.social.KakaoProfile;
import kr.or.watermelon.member.repo.UserJpaRepo;
import kr.or.watermelon.member.service.ResponseService;
import kr.or.watermelon.member.service.social.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID: 이메일", required = true) @RequestParam String email,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = userJpaRepo.findByUid(email).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "회원ID: 이메일", required = true) @RequestParam String email,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name,
                               @ApiParam(value = "폰 번호", required = true) @RequestParam String phoneNo,
                               @ApiParam(value = "생년월일", required = true) @RequestParam String dateOfBirth,
                               @ApiParam(value = "성별", required = true) @RequestParam String gender) {

        userJpaRepo.save(User.builder()
                .uid(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNo(phoneNo)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "소셜 계정 로그인 / 소셜 계정 회원가입", notes = "가입된 회원의 경우 소셜 계정으로 로그인을 한다 / 그렇지 않은 경우 소셜 계정으로 회원가입을 한다.")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> loginProvider(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                              @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        Optional<User> user = userJpaRepo.findByUidAndProvider(String.valueOf(profile.getKakao_account().getEmail()), provider);
        if (user.isPresent()) {
            User presentUser = userJpaRepo.findByUidAndProvider(String.valueOf(profile.getId()), provider).orElseThrow(CUserNotFoundException::new);
            return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(presentUser.getId()), presentUser.getRoles()));
        } else {
            KakaoProfile.Kakao_account account = profile.getKakao_account();
            User inUser = User.builder()
                    .uid(String.valueOf(account.getEmail()))
                    .ageRange(String.valueOf(account.getAge_range()))
                    .gender(String.valueOf(account.getGender()))
                    .provider(provider)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
            User newUser = userJpaRepo.save(inUser);
            return responseService.getSingleResult(newUser.toString());
        }
    }
}