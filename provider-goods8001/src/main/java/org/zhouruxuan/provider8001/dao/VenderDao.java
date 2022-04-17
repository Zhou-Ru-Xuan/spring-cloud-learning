package org.zhouruxuan.provider8001.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhouruxuan.common.entities.Vender;

@Mapper
public interface VenderDao {
    @Select("select * from vender where `id` = #{id}")
    Vender getById(@Param("id") Long id);


}
