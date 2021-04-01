package com.echo.calendar.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.calendar.entity.pojo.MemoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemoDAO extends BaseMapper<MemoEntity> {

}
