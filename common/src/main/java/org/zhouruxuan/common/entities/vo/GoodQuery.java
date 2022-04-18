package org.zhouruxuan.common.entities.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodQuery implements Serializable {
    private String goodName;
    private String venderName;
}
