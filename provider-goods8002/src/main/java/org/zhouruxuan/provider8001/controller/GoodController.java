package org.zhouruxuan.provider8001.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.provider8001.service.GoodService;

@RestController
@RequestMapping("good/provider")
@CrossOrigin
@DefaultProperties(defaultFallback = "goodGlobalFallbackMethod")
public class GoodController {

    @Autowired
    @Qualifier("goodServiceImpl")
    GoodService goodService;


    //根据用户id进行查询
    @GetMapping("getGood/{id}")
    @HystrixCommand(fallbackMethod = "goodTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public R getGood(@PathVariable("id") Long id) {
        Good good = goodService.getById(id);
        return R.ok().data("good 8002", good);
    }


    public R getGoodTimeOutFallbackMethod(@PathVariable("id") Long id) {
        return R.error().data("info", "getGoodTimeOutFallbackMethod");
    }

    // 下面是全局fallback方法
    public R goodGlobalFallbackMethod() {
        return R.error().data("info", "goodGlobalFallbackMethod");
    }
}
