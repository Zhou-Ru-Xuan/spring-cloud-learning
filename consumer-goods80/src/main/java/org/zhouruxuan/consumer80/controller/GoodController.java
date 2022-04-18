package org.zhouruxuan.consumer80.controller;

import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.entities.vo.GoodQuery;
import org.zhouruxuan.common.result.R;
import org.zhouruxuan.consumer80.service.GoodFeignService;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("good/consumer")
public class GoodController {

    @Resource
    private GoodFeignService goodFeignService;

    //根据用户id进行查询
    @GetMapping("getGood/{id}")
    public R getGood(@PathVariable("id") Long id) {
        return goodFeignService.getGood(id);
    }


    @GetMapping("pageGood/{current}/{limit}")
    public R pageListGood(@PathVariable long current,
                          @PathVariable long limit) {
        return goodFeignService.pageListGood(current, limit);
    }

    @PostMapping("pageGoodCondition/{current}/{limit}")
    R pageGoodCondition(@PathVariable long current, @PathVariable long limit,
                        @RequestBody(required = false) GoodQuery goodQuery) {
        return goodFeignService.pageGoodCondition(current, limit, goodQuery);
    }

    @GetMapping("getPageGoodListCondition/{current}/{limit}/{name}/{price}/{venderName}")
    public R getPageGoodListCondition(@PathVariable int current, @PathVariable int limit, @PathVariable String name, @PathVariable Double price, @PathVariable String venderName) {
        return goodFeignService.getPageGoodListCondition(current, limit, name, price, venderName);
    }


    @PostMapping("add")
    public R add(@RequestBody Good good) {
        return goodFeignService.add(good);
    }

    @DeleteMapping("deleteGoodById/{id}")
    public R deleteGoodById(@PathVariable("id") Long id) {
        return goodFeignService.deleteGoodById(id);
    }

    @PostMapping("updateGoodById")
    public R updateGoodById(@RequestBody Good good) {
        return goodFeignService.updateGoodById(good);
    }

    @GetMapping("getVenderNameList")
    public R getVenderNameList() {
        return goodFeignService.getVenderNameList();
    }

    //根据用户id进行查询
    @GetMapping("vue-admin-template/user/login")
    public R login() {
        return R.ok().data("username", "admin").data("password", "111111").data("token", "zhouruxuan");
    }

    //根据用户id进行查询
    @GetMapping("vue-admin-template/user/info")
    public R info() {
        return R.ok().data("name", "软件1908-20195407-周汝轩").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
