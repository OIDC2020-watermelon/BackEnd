package kr.or.watermelon.member.service;

import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.model.social.KakaoProfile;
import kr.or.watermelon.member.model.social.NaverProfile;
import kr.or.watermelon.member.repo.UserJpaRepo;
import kr.or.watermelon.member.service.social.KakaoService;
import kr.or.watermelon.member.service.social.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialSignService {

    private final UserJpaRepo userJpaRepo;
    private final NaverService naverService;
    private final KakaoService kakaoService;

    public User signupByNaver(String provider, String accessToken) {
        NaverProfile naverProfile = naverService.getNaverProfile(accessToken);
        NaverProfile.Response naverAccount = naverProfile.getResponse();
        Optional<User> naverUser = userJpaRepo.findByUidAndProvider(naverAccount.getEmail(), provider);
        if (naverUser.isPresent()) {
            return naverUser.get();
        } else {
            User newUser = User.builder()
                    .uid(String.valueOf(String.valueOf(naverAccount.getEmail())))
                    .name(String.valueOf(naverAccount.getName()))
                    .ageRange(String.valueOf(naverAccount.getAge()))
                    .gender(String.valueOf(naverAccount.getGender()))
                    .provider(provider)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();

            return userJpaRepo.save(newUser);
        }
    }

    public User signupByKakao(String provider, String accessToken) {
        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(accessToken);
        KakaoProfile.Kakao_account kakaoAccount = kakaoProfile.getKakao_account();
        Optional<User> kakaoUser = userJpaRepo.findByUidAndProvider(String.valueOf(kakaoAccount.getEmail()), provider);
        if (kakaoUser.isPresent()) {
            return kakaoUser.get();
        } else {
            User newUser = User.builder()
                    .uid(String.valueOf(kakaoAccount.getEmail()))
                    .ageRange(String.valueOf(kakaoAccount.getAge_range()))
                    .gender(String.valueOf(kakaoAccount.getGender()))
                    .provider(provider)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();

            return userJpaRepo.save(newUser);
        }
    }
}