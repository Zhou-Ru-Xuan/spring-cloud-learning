package org.zhouruxuan.provider8001.service;

import org.springframework.stereotype.Component;
import org.zhouruxuan.common.entities.Vender;

@Component
public interface VenderService {
    Vender getById(Long id);

}
