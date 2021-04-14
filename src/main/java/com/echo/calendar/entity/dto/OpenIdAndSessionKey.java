package com.echo.calendar.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenIdAndSessionKey {

    private String openid;
    private String session_key;
}
