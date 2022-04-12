package org.zhouruxuan.provider8001.service;

import org.springframework.stereotype.Component;
import org.zhouruxuan.common.entities.Good;

@Component
public interface GoodService {

    Good getById(Long id);


}
