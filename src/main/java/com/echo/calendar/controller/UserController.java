package com.echo.calendar.controller;


import com.echo.calendar.entity.bean.CommonResult;
import com.echo.calendar.entity.vo.UserLoginVO;
import com.echo.calendar.service.impl.UserServiceImpl;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public CommonResult<UserLoginVO> login(@RequestBody String code) throws JSONException {
        UserLoginVO userLoginVO = userService.login(code);
        return new CommonResult<>(200,"OK",userLoginVO);
    }
}
