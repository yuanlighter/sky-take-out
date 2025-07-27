package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.admin.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:50
 * @Description:
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "后台菜品相关接口")
public class DishController {
    @Autowired
    private DishService dishService;

    @ApiOperation("新增菜品及口味")
    @PostMapping
    public Result<String> add(@RequestBody DishDTO dishDTO){
        return dishService.addWithFlavors(dishDTO);
    }
}
