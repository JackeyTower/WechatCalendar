package com.echo.calendar.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.calendar.entity.pojo.MemoEntity;
import com.echo.calendar.entity.vo.MemoQueryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemoDAO extends BaseMapper<MemoEntity> {

    @Select("select time,text,title,mid from memo_entity where openid = #{openid}")
    List<MemoQueryVO> findAllByOpenid(@Param("openid") String openid);
}
