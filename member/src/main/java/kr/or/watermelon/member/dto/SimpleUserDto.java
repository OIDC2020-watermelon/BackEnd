package kr.or.watermelon.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "SimpleUserDto", description = "사용자 아이디(pkey) & 이름 ")
public class SimpleUserDto {

    @ApiModelProperty(value = "아이디(pkey)")
    private long id;
    @ApiModelProperty(value = "이름")
    private String name;
}