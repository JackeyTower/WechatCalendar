package com.echo.calendar.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

    @TableId
    @ApiModelProperty("小程序用户id")
    String openid;
    String nickname;
    String skey;
    Long create_time;
    Long last_visit_time;
    String session_key;
    String city;
    String country;
    String province;
    String gender;
    String avatar_url;


}
