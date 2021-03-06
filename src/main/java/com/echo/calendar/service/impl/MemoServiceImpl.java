package com.echo.calendar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.calendar.dao.MemoDAO;
import com.echo.calendar.entity.bean.PageResult;
import com.echo.calendar.entity.pojo.MemoEntity;
import com.echo.calendar.entity.vo.MemoQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl extends ServiceImpl<MemoDAO, MemoEntity> {

    private MemoDAO memoDAO;

    public MemoServiceImpl(MemoDAO memoDAO) {
        this.memoDAO = memoDAO;
    }


    public PageResult<MemoQueryVO> findAllMemo(String openid, Integer cp, Integer ps){

        Page<Object> page = PageHelper.startPage(cp, ps);
        List<MemoQueryVO> allByOpenid = memoDAO.findAllByOpenid(openid);

        return new PageResult<>(page.getPages(), page.getTotal(), allByOpenid);
    }
}
