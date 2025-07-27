package com.sky.mapper.common;

import com.sky.annotation.AutoFill;
import com.sky.enumeration.OperationType;
import com.sky.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 增加
     * @param dish
     * @return
     */
    @AutoFill(OperationType.INSERT)
    int insert(Dish dish);

    /**
     * 根据名称统计菜品数量
     * @param name
     * @return
     */
    @Select("select count(id) from dish where name = #{name}")
    Long countByName(String name);
}
