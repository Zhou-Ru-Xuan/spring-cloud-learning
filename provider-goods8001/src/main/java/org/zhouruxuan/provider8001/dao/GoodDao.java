package org.zhouruxuan.provider8001.dao;

import org.apache.ibatis.annotations.*;
import org.zhouruxuan.common.entities.Good;


@Mapper
public interface GoodDao  {
    @Select("select * from good where  `id` = #{id}")
    Good getById(@Param("id") Long id);

    @Update("delete from good where id = #{id}")
    boolean deleteById(@Param("id") Long id);

    @Insert("insert into good values(null,#{good.goodName},#{good.price},#{good.venderName})")
    boolean add(@Param("good") Good good);

    @Update("update good set  `good_name` = #{good.goodName} , `price` = #{good.price} , `vender_name` = #{good.venderName}  where `id` = #{good.id}")
    boolean updateById(@Param("good") Good good);



}

