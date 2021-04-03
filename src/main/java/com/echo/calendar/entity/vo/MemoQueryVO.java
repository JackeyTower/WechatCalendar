package com.echo.calendar.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoQueryVO {
    String time;
    String text;
    String title;
    String mid;
}
