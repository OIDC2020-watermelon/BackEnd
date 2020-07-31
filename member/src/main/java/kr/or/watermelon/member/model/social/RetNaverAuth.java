package kr.or.watermelon.member.model.social;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetNaverAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
}
