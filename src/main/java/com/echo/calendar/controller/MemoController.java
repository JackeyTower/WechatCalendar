package com.echo.calendar.controller;

import com.echo.calendar.entity.bean.CommonResult;
import com.echo.calendar.entity.bean.PageResult;
import com.echo.calendar.entity.dto.MemoAddDTO;
import com.echo.calendar.entity.dto.MemoUpdateDTO;
import com.echo.calendar.entity.pojo.MemoEntity;
import com.echo.calendar.entity.vo.MemoQueryVO;
import com.echo.calendar.service.impl.MemoServiceImpl;
import com.echo.calendar.util.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<String> addMemo(@RequestBody MemoAddDTO memoAddDTO) {
        String mid = idWorker.nextId() + "";
        MemoEntity memoEntity = new MemoEntity();
        memoEntity.setMid(mid);
        BeanUtils.copyProperties(memoAddDTO, memoEntity);
        memoService.save(memoEntity);
        return new CommonResult<>(200, "OK", "mid");
    }

    @DeleteMapping("/{mid}")
    public CommonResult<String> deleteMemo(@PathVariable("mid") String mid) {
        memoService.removeById(mid);
        return new CommonResult<>(200, "OK", "删除成功");
    }

    @PutMapping("/update")
    public CommonResult<String> UpdateMemo(@RequestBody MemoUpdateDTO memoUpdateDTO) {
        memoService.updateById(memoUpdateDTO);
        return new CommonResult<>(200, "OK", "更新成功");
    }

    @GetMapping("/{openid}/{cp}/{ps}")
    public CommonResult<PageResult<MemoQueryVO>> findAllMemo(@PathVariable("openid") String openid,
                                                             @PathVariable("cp") Integer cp,
                                                             @PathVariable("ps") Integer ps){

        PageResult<MemoQueryVO> allMemo = memoService.findAllMemo(openid, cp, ps);
        return new CommonResult<>(200,"OK",allMemo);
    }


}