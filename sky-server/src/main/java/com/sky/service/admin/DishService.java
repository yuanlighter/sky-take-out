package com.sky.service.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:52
 * @Description:
 */

public interface DishService {
    Result<String> addWithFlavors(DishDTO dishDTO);
}
