package org.zhouruxuan.provider8002.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.provider8002.service.GoodService;

@RestController
@RequestMapping("good/provider")
@CrossOrigin
public class GoodController {

    @Autowired
    @Qualifier("goodServiceImpl")
    GoodService goodService;


    //根据用户id进行查询
    @GetMapping("getGood/{id}")
    public R getGood(@PathVariable("id") Long id) {
        Good good = goodService.getById(id);
        return R.ok().data("good 8002", good);
    }

}
