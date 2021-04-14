package com.echo.calendar.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoEntity {

    @TableId
    @ApiModelProperty("备忘录id")
    String mid;
    @ApiModelProperty("备忘录标题")
    String title;
    @ApiModelProperty("备忘录正文")
    String text;
    @ApiModelProperty("备忘录创建时间")
    String time;
    @ApiModelProperty("小程序用户id")
    String openid;
}
