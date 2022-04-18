package org.zhouruxuan.consumer80.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.entities.vo.GoodQuery;
import org.zhouruxuan.common.result.R;

@Component
@FeignClient(value = "PROVIDER-GOODS", fallback = GoodFallbackServiceImpl.class)
public interface GoodFeignService {
    @GetMapping("good/provider/getGood/{id}")
    R getGood(@PathVariable("id") Long id);


    @GetMapping("good/provider/pageGood/{current}/{limit}")
    R pageListGood(@PathVariable ("current")long current,
                          @PathVariable("limit")  long limit);

    @PostMapping("good/provider/pageGoodCondition/{current}/{limit}")
    R pageGoodCondition(@PathVariable("current") long current,  @PathVariable("limit") long limit,
                        @RequestBody(required = false) GoodQuery goodQuery);

    @GetMapping("good/provider/getPageGoodListCondition/{current}/{limit}/{name}/{price}/{venderName}")
    R getPageGoodListCondition(@PathVariable("current") int current, @PathVariable("limit") int limit, @PathVariable("name") String name, @PathVariable("price") Double price, @PathVariable("venderName") String venderName);


    @GetMapping("vender/provider/getVenderNameList")
    R getVenderNameList();


    @PostMapping("good/provider/add")
    R add(@RequestBody Good good);

    @DeleteMapping("good/provider/deleteGoodById/{id}")
     R deleteGoodById(@PathVariable("id") Long id) ;

    @PostMapping("good/provider/updateGoodById")
     R updateGoodById(@RequestBody Good good);

}
