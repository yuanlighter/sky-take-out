package com.sky.shoturi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sky.shorturi")
@Component
@Data
public class ShortUrlProperties {

    private  String domainName; // 域名
    private  String shortUrlPrefix ;
    private  String realResource ;
}
