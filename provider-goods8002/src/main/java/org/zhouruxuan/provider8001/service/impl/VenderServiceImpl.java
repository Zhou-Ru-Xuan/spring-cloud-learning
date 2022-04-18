package org.zhouruxuan.provider8001.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zhouruxuan.common.entities.Vender;
import org.zhouruxuan.provider8001.dao.VenderDao;
import org.zhouruxuan.provider8001.service.VenderService;

@Service
public class VenderServiceImpl extends ServiceImpl<VenderDao, Vender> implements VenderService {
}
