package com.echo.calendar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.echo.calendar.dao")
public class CalendarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class,args);
    }
}
