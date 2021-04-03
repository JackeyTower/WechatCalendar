package com.echo.calendar.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {
    @ApiModelProperty("小程序用户id")
    String oepnid;
    @ApiModelProperty("用户名")
    String username;
}
