package com.echo.calendar.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @ApiModelProperty("小程序用户id")
    String oepnid;
    @ApiModelProperty("用户名")
    String username;

}
