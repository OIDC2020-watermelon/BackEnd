package kr.or.watermelon.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "UserIdDto", description = "사용자 아이디(pkey)")
public class UserIdDto {

    @ApiModelProperty(value = "아이디(pkey)")
    private long id;
}