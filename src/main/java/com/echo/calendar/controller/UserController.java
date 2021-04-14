package com.echo.calendar.controller;


import com.echo.calendar.entity.bean.CommonResult;
import com.echo.calendar.entity.dto.UserAddDTO;
import com.echo.calendar.entity.pojo.UserEntity;
import com.echo.calendar.service.impl.UserServiceImpl;
import com.echo.calendar.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "用户模块")
@Slf4j
@Component
@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;
    private RedisUtil redisUtil;

    public UserController(UserServiceImpl userService, RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    //返回token
    @ApiOperation(value = "用户登录")
    @RequestMapping("/login")
    public CommonResult<String> login(@RequestParam String code,
                                      @RequestParam String rawData,
                                      @RequestParam String signature) throws JSONException {
        String token = userService.login(code,rawData,signature);
        if (token.equals("")){
            return new CommonResult<>(500,"用户信息不完整","");
        }
        System.out.println(token);
        return new CommonResult<>(200,"OK",token);
    }

    //这里出大问题，登录逻辑要改
    @ApiOperation(value = "添加用户并回显")
    @ApiImplicitParam(name = "userAddDTO",value = "添加用户所需信息",dataType = "UserAddDTO")
    @RequestMapping("/addUser")
    public CommonResult<String> addUser(@RequestBody UserAddDTO userAddDTO, HttpServletRequest request){
//        String openid = request.getAttribute("openid") + "";
        String openid="1";

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userAddDTO,userEntity);
        userEntity.setOpenid(openid);
        //更新用户信息
        userService.saveOrUpdate(userEntity);

        return new CommonResult<>(200,"OK",userEntity.getUsername());
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping("/delete")
    public CommonResult<String> deleteUser(HttpServletRequest request) {
//        String openid = request.getAttribute("openid") + "";
        String openid="1";

        userService.removeById(openid);
        return new CommonResult<>(200,"OK","删除成功");
    }

    @RequestMapping("/redis")
    public void redisTest(){
        redisUtil.set("aa","aaa");
        String aa = redisUtil.get("aa")+"";
        System.out.println(aa);
    }
}
