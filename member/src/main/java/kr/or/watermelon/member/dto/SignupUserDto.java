package kr.or.watermelon.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "SignupUserDto", description = "사용자")
public class SignupUserDto {
    @ApiModelProperty(value = "아이디(이메일)")
    private String uid;
    @ApiModelProperty(value = "비밀번호")
    private String password;
    @ApiModelProperty(value = "이름")
    private String name;
}