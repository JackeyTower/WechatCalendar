package com.echo.calendar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.calendar.dao.MemoDAO;
import com.echo.calendar.entity.dto.MemoUpdateDTO;
import com.echo.calendar.entity.pojo.MemoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl extends ServiceImpl<MemoDAO, MemoEntity> {

    private MemoDAO memoDAO;

    public MemoServiceImpl(MemoDAO memoDAO) {
        this.memoDAO = memoDAO;
    }

    public void updateById(MemoUpdateDTO memoUpdateDTO){
        MemoEntity memoEntity = new MemoEntity();
        BeanUtils.copyProperties(memoUpdateDTO,memoEntity);
        memoDAO.updateById(memoEntity);
    }
}
