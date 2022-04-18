package org.zhouruxuan.provider8001.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.provider8001.service.GoodService;

import java.util.List;

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
        return R.ok().data("good 8002", good);
    }

    @PostMapping("add")
    public R add(@RequestBody Good good) {
        return R.ok().data("add 8002", goodService.save(good));
    }

    @DeleteMapping("deleteGoodById/{id}")
    public R deleteGoodById(@PathVariable("id") Long id) {
        return R.ok().data("deleteGoodById 8002", goodService.removeById(id));
    }

    @PostMapping("updateGoodById")
    public R updateGoodById(@RequestBody Good good) {
        return R.ok().data("updateGoodById 8002", goodService.updateById(good));
    }


    // 条件查询带分页的方法
    @GetMapping("getPageGoodListCondition/{current}/{limit}/{name}/{price}/{venderName}")
    public R getPageGoodListCondition(@PathVariable long current, @PathVariable long limit, @PathVariable String name, @PathVariable Double price, @PathVariable String venderName) {
        Page<Good> pageGood = new Page<>(current, limit);

        QueryWrapper<Good> wrapper = new QueryWrapper<>();

        if (!name.equals("null")) {
            wrapper.like("good_name", name);
        }
        if (price >= 0) {
            wrapper.eq("price", price);
        }
        if (!venderName.equals("null")) {
            wrapper.eq("vender_name", venderName);
        }

        //调用方法实现条件查询分页
        goodService.page(pageGood, wrapper);
        List<Good> records = pageGood.getRecords(); //数据list集合
        return R.ok().data("goods", records);
    }


    public R getGoodTimeOutFallbackMethod(@PathVariable("id") Long id) {
        return R.error().data("info", "getGoodTimeOutFallbackMethod");
    }

    // 下面是全局fallback方法
    public R goodGlobalFallbackMethod() {
        return R.error().data("info", "goodGlobalFallbackMethod");
    }
}
