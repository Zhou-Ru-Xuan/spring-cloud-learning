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
@DefaultProperties(defaultFallback = "goodGlobalFallbackMethod")//降级
public class GoodController {

    @Autowired
    @Qualifier("goodServiceImpl")
    GoodService goodService;


    //根据用户id进行查询
    @GetMapping("getGood/{id}")
    @HystrixCommand(fallbackMethod = "getGoodTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//超时降级
            
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public R getGood(@PathVariable("id") Long id) {
        Good good = goodService.getById(id);
        return R.ok().data("good 8001", good);
    }


    public R getGoodTimeOutFallbackMethod(@PathVariable("id") Long id) {
        return R.error().data("info", "getGoodTimeOutFallbackMethod");
    }

    // 下面是全局fallback方法
    public R goodGlobalFallbackMethod() {
        return R.error().data("info", "goodGlobalFallbackMethod");
    }
}
