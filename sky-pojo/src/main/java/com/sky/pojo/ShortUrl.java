package com.sky.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  录入 短链接和 长链接 信息
 */
@Data
@Builder
public class ShortUrl {
    private Long id;

    private String shortUrl; //  短链码
    private String fullUrl; //  完整链接
    private String shortCode;
    private LocalDateTime expireTime;
    private Long tradeNo; //运单唯一标识

}
