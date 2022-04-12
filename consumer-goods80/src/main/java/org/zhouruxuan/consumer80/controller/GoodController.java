package org.zhouruxuan.consumer80.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.consumer80.service.GoodFeignService;

import javax.annotation.Resource;

@RestController
@RequestMapping("good/consumer")
public class GoodController {

    @Resource
    private GoodFeignService goodFeignService;

    //根据用户id进行查询
    @GetMapping("getGood/{id}")
    public R getGood(@PathVariable("id") Long id) {
        return goodFeignService.getGood(id);
    }

}
