package com.echo.calendar.controller;

import com.echo.calendar.entity.bean.CommonResult;
import com.echo.calendar.entity.bean.PageResult;
import com.echo.calendar.entity.dto.MemoAddDTO;
import com.echo.calendar.entity.dto.MemoUpdateDTO;
import com.echo.calendar.entity.pojo.MemoEntity;
import com.echo.calendar.entity.vo.MemoQueryVO;
import com.echo.calendar.service.impl.MemoServiceImpl;
import com.echo.calendar.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/memo")
public class MemoController {

    private MemoServiceImpl memoService;
    private IdWorker idWorker;

    public MemoController(MemoServiceImpl memoService, IdWorker idWorker) {
        this.memoService = memoService;
        this.idWorker = idWorker;
    }

    @RequestMapping("/add")
    public CommonResult<String> addMemo(@RequestBody MemoAddDTO memoAddDTO, HttpServletRequest request) {
//        String openid = request.getAttribute("openid")+"";
        String openid="1";

        String mid = idWorker.nextId() + "";

        MemoEntity memoEntity = new MemoEntity();

        BeanUtils.copyProperties(memoAddDTO, memoEntity);
        memoEntity.setMid(mid);
        memoEntity.setOpenid(openid);

        memoService.save(memoEntity);
        return new CommonResult<>(200, "OK", "mid");
    }

    @DeleteMapping("/{mid}")
    public CommonResult<String> deleteMemo(@PathVariable("mid") String mid) {
        memoService.removeById(mid);
        return new CommonResult<>(200, "OK", "删除成功");
    }

    @PutMapping("/update")
    public CommonResult<String> UpdateMemo(@RequestBody MemoUpdateDTO memoUpdateDTO,HttpServletRequest request) {
        MemoEntity memoEntity = new MemoEntity();
        BeanUtils.copyProperties(memoUpdateDTO,memoEntity);
        String openid = request.getAttribute("openid") + "";
        memoEntity.setOpenid(openid);
        memoService.updateById(memoEntity);
        return new CommonResult<>(200, "OK", "更新成功");
    }

    @GetMapping("/{cp}/{ps}")
    public CommonResult<PageResult<MemoQueryVO>> findAllMemo(@PathVariable("cp") Integer cp,
                                                             @PathVariable("ps") Integer ps,HttpServletRequest request){
//        String openid = request.getAttribute("openid") + "";
        String openid="1";
        PageResult<MemoQueryVO> allMemo = memoService.findAllMemo(openid, cp, ps);
        return new CommonResult<>(200,"OK",allMemo);
    }


}