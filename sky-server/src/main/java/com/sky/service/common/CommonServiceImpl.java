package com.sky.service.common;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:30
 * @Description:
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService{
    @Autowired
    private AliOssUtil aliOssUtil;
    @Override
    public Result<String> upload(byte[] bytes, String filename) {
        String suffix = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID().toString().replace("-","").concat(suffix);
        log.warn("--->filename:{}",filename);
        String url = aliOssUtil.upload(bytes, filename);
        return Result.success(url);
    }
}
