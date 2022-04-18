package org.zhouruxuan.common.entities;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Good implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String goodName;
    private Double price;
    private String venderName;
}
