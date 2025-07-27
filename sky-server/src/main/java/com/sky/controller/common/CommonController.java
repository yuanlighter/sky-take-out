package com.sky.controller.common;

import com.sky.exception.BaseException;
import com.sky.result.Result;
import com.sky.service.common.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: yuan
 * @Date: 2025-07-27 11:24
 * @Description:
 */

@RestController
@Slf4j
@Api(tags="公共接口")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @ApiOperation("文件上传")
    @PostMapping("/admin/common/upload")
    public Result<String>upload(@RequestParam(name = "file")MultipartFile file){
        try {
            return commonService.upload(file.getBytes(),file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("文件上传失败:"+e.getMessage());
        }
    }
}
