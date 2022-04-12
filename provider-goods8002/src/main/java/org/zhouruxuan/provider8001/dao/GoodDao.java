package org.zhouruxuan.provider8001.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zhouruxuan.common.entities.Good;


@Mapper
public interface GoodDao {
    @Select("select * from good where `id` = #{id}")
    Good getById(Long id);

}
