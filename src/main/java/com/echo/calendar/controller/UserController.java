package com.echo.calendar.controller;


import com.echo.calendar.entity.bean.CommonResult;
import com.echo.calendar.entity.dto.UserAddDTO;
import com.echo.calendar.entity.pojo.UserEntity;
import com.echo.calendar.entity.vo.UserLoginVO;
import com.echo.calendar.service.UserService;
import com.echo.calendar.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "用户模块")
@Component
@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "code",value = "wx.login获得的登录凭证code",dataType = "String")
    @RequestMapping("/login")
    public CommonResult<UserLoginVO> login(@RequestBody String code) throws JSONException {
        UserLoginVO userLoginVO = userService.login(code);
        return new CommonResult<>(200,"OK",userLoginVO);
    }

    @ApiOperation(value = "添加用户并回显")
    @ApiImplicitParam(name = "userAddDTO",value = "添加用户所需信息",dataType = "UserAddDTO")
    @RequestMapping("/addUser")
    public CommonResult<UserEntity> addUser(@RequestBody UserAddDTO userAddDTO){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userAddDTO,userEntity);
        return new CommonResult<>(200,"OK",userEntity);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "openid",value = "用户唯一标识",dataType = "String")
    @RequestMapping("/{openid}/delete")
    public CommonResult<String> deleteUser(@PathVariable("openid") String openid) {
        userService.removeById(openid);
        return new CommonResult<>(200,"OK","删除成功");
    }

    }
