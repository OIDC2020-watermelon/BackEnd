package kr.or.watermelon.show.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "SimpleUserDto", description = "사용자 아이디 (pkey) & 사용자 이름 ")
public class SimpleUserDto {

    @ApiModelProperty(value = "아이디")
    private long id;
    @ApiModelProperty(value = "이름")
    private String name;
}