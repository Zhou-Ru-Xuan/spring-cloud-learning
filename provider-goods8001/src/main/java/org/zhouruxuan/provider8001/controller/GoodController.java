package org.zhouruxuan.provider8001.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.entities.vo.GoodQuery;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.provider8001.service.GoodService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return R.ok().data("good", good).data("info","get from 8001");
    }

    @PostMapping("add")
    @HystrixCommand
    public R add(@RequestBody Good good) {
        return R.ok().data("add 8001", goodService.save(good));
    }

    @DeleteMapping("deleteGoodById/{id}")
    @HystrixCommand
    public R deleteGoodById(@PathVariable("id") Long id) {
        return R.ok().data("deleteGoodById 8001", goodService.removeById(id));
    }

    @PostMapping("updateGoodById")
    @HystrixCommand
    public R updateGoodById(@RequestBody Good good) {
        return R.ok().data("updateGoodById 8001", goodService.updateById(good));
    }


    @GetMapping("pageGood/{current}/{limit}")
    @HystrixCommand
    public R pageListGood(@PathVariable long current,
                          @PathVariable long limit) {
        //创建page对象
        Page<Good> pageGood = new Page<>(current, limit);

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageGood对象里面
        goodService.page(pageGood, null);

        long total = goodService.count();//总记录数
        List<Good> records = pageGood.getRecords(); //数据list集合

        Map<String, Object> map = new HashMap();
        map.put("total", total);
        map.put("goods", records);
        return R.ok().data(map);

    }


    // 条件查询带分页的方法
    @HystrixCommand
    @PostMapping("pageGoodCondition/{current}/{limit}")
    public R pageGoodCondition(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) GoodQuery goodQuery) {
        //创建page对象
        Page<Good> pageGood = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String goodName = goodQuery.getGoodName();
        String venderName = goodQuery.getVenderName();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(goodName)) {
            wrapper.like("good_name", goodName);
        }
        if (!StringUtils.isEmpty(venderName)) {
            wrapper.like("vender_name", venderName);
        }

        //调用方法实现条件查询分页
        goodService.page(pageGood, wrapper);

        long total = goodService.count();//总记录数

        List<Good> records = pageGood.getRecords(); //数据list集合
        return R.ok().data("total", total).data("goods", records);
    }

    // 条件查询带分页的方法
    @HystrixCommand
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
            wrapper.like("vender_name", venderName);
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
