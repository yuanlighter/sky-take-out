package com.sky.service.common;

import com.sky.result.Result;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:29
 * @Description:
 */

public interface CommonService {
    Result<String> upload(byte[] bytes, String originalFilename);
}
