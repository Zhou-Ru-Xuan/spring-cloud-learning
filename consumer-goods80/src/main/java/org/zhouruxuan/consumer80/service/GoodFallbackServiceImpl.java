package org.zhouruxuan.consumer80.service;

import org.springframework.stereotype.Component;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.common.entities.vo.GoodQuery;
import org.zhouruxuan.common.result.R;

@Component
public class GoodFallbackServiceImpl implements GoodFeignService {
    @Override
    public R getGood(Long id) {
        return R.error().data("info", "[consumer80]  Method : public R getGood(Long id) , fallback");
    }

    @Override
    public R pageListGood(long current, long limit) {
        return R.error().data("info", "[consumer80]  Method :  public R pageListGood(long current, long limit) , fallback");
    }

    @Override
    public R pageGoodCondition(long current, long limit, GoodQuery goodQuery) {
        return R.error();
    }

    @Override
    public R getPageGoodListCondition(int current, int limit, String name, Double price, String venderName) {
        return R.error();
    }

    @Override
    public R getVenderNameList() {
        return R.error();
    }

    @Override
    public R add(Good good) {
        return R.error().data("info", "[consumer80]  Method :  public R add(Good good) , fallback");
    }

    @Override
    public R deleteGoodById(Long id) {
        return null;
    }

    @Override
    public R updateGoodById(Good good) {
        return null;
    }

}
