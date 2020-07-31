package kr.or.watermelon.member.controller.common;

import kr.or.watermelon.member.service.social.KakaoService;
import kr.or.watermelon.member.service.social.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * 프론트 없이 development 때 사용: 소셜 로그인 화면 & 토큰 받기
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/social/login")
public class SocialController {

    private final Environment env;
    private final KakaoService kakaoService;
    private final NaverService naverService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    @Value("${spring.social.naver.client_id}")
    private String naverClientId;

    @Value("${spring.social.naver.client_secret}")
    private String naverClientSecret;

    @Value("${spring.social.naver.redirect}")
    private String naverRedirect;

    /**
     * 카카오 로그인 페이지
     */
    @GetMapping
    public ModelAndView socialLogin(ModelAndView mav) {

        StringBuilder kakaoLoginUrl = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

        mav.addObject("kakaoLoginUrl", kakaoLoginUrl);


        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        StringBuilder naverLoginUrl = new StringBuilder()
                .append(env.getProperty("spring.social.naver.url.login"))
                .append("?response_type=code")
                .append("&client_id=").append(naverClientId)
                .append("&redirect_uri=").append("http://localhost:8080").append(naverRedirect)
                .append("&state=").append(state);

        mav.addObject("naverLoginUrl", naverLoginUrl);

        mav.setViewName("social/login");
        return mav;
    }

    /**
     * 카카오 인증 완료 후 리다이렉트 화면
     */
    @GetMapping(value = "/kakao")
    public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code) {
        mav.addObject("authInfo", kakaoService.getKakaoTokenInfo(code));
        mav.setViewName("social/redirectKakao");
        return mav;
    }

    /**
     * 네이 인증 완료 후 리다이렉트 화면
     */
    @GetMapping(value = "/naver")
    public ModelAndView redirectNaver(ModelAndView mav, @RequestParam String code, @RequestParam String state) {
        mav.addObject("authInfo", naverService.getNaverTokenInfo(code, state));
        mav.setViewName("social/redirectNaver");
        return mav;
    }
}
