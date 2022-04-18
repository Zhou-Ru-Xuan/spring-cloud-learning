package org.zhouruxuan.provider8001.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Vender;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.provider8001.service.VenderService;

@RestController
@RequestMapping("vender/provider")
@CrossOrigin
@DefaultProperties(defaultFallback = "venderGlobalFallbackMethod")//降级
public class VenderController {

    @Autowired
    @Qualifier("venderServiceImpl")
    VenderService venderService;


    //根据用户id进行查询
    @GetMapping("getVender/{id}")
    @HystrixCommand(fallbackMethod = "getVenderTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//超时降级

            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public R getVender(@PathVariable("id") Long id) {
        Vender vender = venderService.getById(id);
        return R.ok().data("vender 8002", vender);
    }


    public R getVenderTimeOutFallbackMethod(@PathVariable("id") Long id) {
        return R.error().data("info", "getVenderTimeOutFallbackMethod");
    }

    // 下面是全局fallback方法
    public R venderGlobalFallbackMethod() {
        return R.error().data("info", "venderGlobalFallbackMethod");
    }
}
