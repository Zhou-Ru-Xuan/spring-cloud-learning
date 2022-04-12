package org.zhouruxuan.consumer80.service;

import org.springframework.stereotype.Component;
import org.zhouruxuan.common.result.R;

@Component
public class GoodFallbackServiceImpl implements GoodFeignService {
    @Override
    public R getGood(Long id) {
        return R.error().data("info", "[consumer80]  Method : public R getGood(Long id) , fallback");
    }
}
