package com.echo.calendar.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.calendar.entity.pojo.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<UserEntity> {
}
