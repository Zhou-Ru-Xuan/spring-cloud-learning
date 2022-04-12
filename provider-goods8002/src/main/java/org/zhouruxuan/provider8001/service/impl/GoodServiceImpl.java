package org.zhouruxuan.provider8001.service.impl;

import org.springframework.stereotype.Service;
import org.zhouruxuan.common.entities.Good;
import org.zhouruxuan.provider8001.dao.GoodDao;
import org.zhouruxuan.provider8001.service.GoodService;

import javax.annotation.Resource;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    GoodDao dao;

    @Override
    public Good getById(Long id) {
        return dao.getById(id);
    }




}
