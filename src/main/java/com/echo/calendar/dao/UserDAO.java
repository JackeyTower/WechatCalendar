package com.echo.calendar.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.calendar.entity.pojo.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO extends BaseMapper<UserEntity> {
}
