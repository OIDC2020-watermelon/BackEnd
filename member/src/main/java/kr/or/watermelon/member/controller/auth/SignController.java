package kr.or.watermelon.member.controller.auth;

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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    @PostMapping(value = "/signin")
    public SingleResult<String> signin(String email, String password) {

        User user = userJpaRepo.findByUid(email).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
    }

    @PostMapping(value = "/signup")
    public CommonResult signup(String email, String password, String name, String phoneNo, String dateOfBirth, String gender) {

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

    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> loginProvider(String provider, String accessToken) {

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