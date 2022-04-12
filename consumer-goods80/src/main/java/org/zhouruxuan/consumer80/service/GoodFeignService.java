package org.zhouruxuan.consumer80.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zhouruxuan.common.result.R;

@Component
@FeignClient(value = "PROVIDER-GOODS",fallback = GoodFallbackServiceImpl.class)
public interface GoodFeignService
{
    @GetMapping("good/provider/getGood/{id}")
    R getGood(@PathVariable("id") Long id);

}
