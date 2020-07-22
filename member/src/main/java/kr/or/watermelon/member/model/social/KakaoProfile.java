package kr.or.watermelon.member.model.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile {
    private Long id;
    private Properties properties;
    private Kakao_account kakao_account;

    @Getter
    @Setter
    @ToString
    private static class Properties {
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }

    @Getter
    @Setter
    @ToString
    public static class Kakao_account {
        private String email;
        private String birthday;
        private String age_range;
        private String gender;
    }
}
