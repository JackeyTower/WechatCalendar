package com.echo.calendar.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
    @ApiModelProperty("用户唯一标识")
    private String openid;
    @ApiModelProperty("会话密钥")
    private String session_key;

}
