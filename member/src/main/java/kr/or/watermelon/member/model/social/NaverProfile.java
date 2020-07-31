package kr.or.watermelon.member.model.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverProfile {
    private String resultcode;
    private String message;
    private Response response;

    @Getter
    @Setter
    @ToString
    public static class Response {
        private String name;
        private String email;
        private String gender;
        private String age;
        private String birthday;
    }
}
