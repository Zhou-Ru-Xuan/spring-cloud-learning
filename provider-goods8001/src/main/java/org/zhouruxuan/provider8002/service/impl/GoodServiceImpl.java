package org.zhouruxuan.provider8002.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.provider8002.dao.GoodDao;
import org.zhouruxuan.provider8002.service.GoodService;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodDao, Good> implements GoodService {

}
