package kr.or.watermelon.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "UserDto", description = "사용자")
public class UserDto {

    @ApiModelProperty(value = "이름")
    private String name;
    @ApiModelProperty(value = "모바일 번호")
    private String phoneNo;
    @ApiModelProperty(value = "생년월일")
    private String dateOfBirth;
    @ApiModelProperty(value = "성별")
    private String gender;
    @ApiModelProperty(value = "사용자 권한", required = false)
    private List<String> roles = new ArrayList<>();
}