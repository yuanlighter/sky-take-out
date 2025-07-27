package com.sky.service.admin.impl;

import com.sky.dto.DishDTO;
import com.sky.exception.ParamsIllegalException;
import com.sky.mapper.common.DishFlavorMapper;
import com.sky.mapper.common.DishMapper;
import com.sky.pojo.Dish;
import com.sky.pojo.DishFlavor;
import com.sky.result.Result;
import com.sky.service.admin.DishService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:52
 * @Description:
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<String> addWithFlavors(DishDTO dishDTO) {
        // 一.校验参数
        if(Objects.isNull(dishDTO)
                || Objects.isNull(dishDTO.getCategoryId())
                || StringUtils.isBlank(dishDTO.getName())
                || Objects.isNull(dishDTO.getPrice())
                || StringUtils.isBlank(dishDTO.getImage())){
            throw new ParamsIllegalException("参数非法");
        }
        //1.保存菜品数据到菜品表
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);
        // 2.保存菜品与口味数据到菜品口味表
        // 为每个口味都设置菜品ID
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dish.getId());
        });
        dishFlavorMapper.insertBatch(flavors);
        // 三.封装数据
        return Result.success();
    }
}
