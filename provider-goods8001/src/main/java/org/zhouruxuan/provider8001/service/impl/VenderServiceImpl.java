package org.zhouruxuan.provider8001.service.impl;

import org.springframework.stereotype.Service;
import org.zhouruxuan.common.entities.Vender;
import org.zhouruxuan.provider8001.dao.VenderDao;
import org.zhouruxuan.provider8001.service.VenderService;

import javax.annotation.Resource;

@Service
public class VenderServiceImpl implements VenderService {
    @Resource
    VenderDao venderDao;

    @Override
    public Vender getById(Long id) {
        return venderDao.getById(id);
    }
}
