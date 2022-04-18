package org.zhouruxuan.consumer80.controller;

import org.springframework.web.bind.annotation.*;
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


    @GetMapping("getPageGoodListCondition/{current}/{limit}/{name}/{price}/{venderName}")
    public R getPageGoodListCondition(@PathVariable int current, @PathVariable int limit, @PathVariable String name, @PathVariable Double price, @PathVariable String venderName) {
        return goodFeignService.getPageGoodListCondition(current, limit, name, price, venderName);
    }


}
