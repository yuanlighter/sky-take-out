package com.sky.shoturi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
@Slf4j
public class ShortUrlGenerator {

    @Autowired
    private ShortUrlProperties shortUriProperties;


    private  String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public   String creatKey() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(BASE62.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }


    public  String getFullUrl(Long tradeNo, Long courierId, LocalDateTime expireTime){
        StringBuilder fullUrl = new StringBuilder();
        // 拼接域名 https://www.xxx.com/
        fullUrl.append(shortUriProperties.getDomainName());
        //拼接真实资源地址  https://www.xxx.com/rider.html
        fullUrl.append(shortUriProperties.getRealResource());
        //拼接参数 https://www.xxx.com/rider.html?tradeNo=
        fullUrl.append("?tradeNo=");
        //拼接参数 https://www.xxx.com/rider.html?tradeNo=90
        fullUrl.append(tradeNo);

        //拼接参数  https://www.xxx.com/rider.html?tradeNo=90&courierId=
        fullUrl.append("&courierId=");
        fullUrl.append(courierId);

        //拼接参数 https://www.xxx.com/rider.html?tradeNo=90&courierId=8&expire=
        fullUrl.append("&expire=");
        String expire = expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        fullUrl.append(expire);

        return fullUrl.toString();
    }

    public  String getShortUrl(String code){
        StringBuilder shortUrl = new StringBuilder();
        //拼接域名  https://www.xxx.com
        shortUrl.append(shortUriProperties.getDomainName());
        //拼接短链前缀   https://www.xxx.com/r/
        shortUrl.append(shortUriProperties.getShortUrlPrefix());
        //拼接code
        shortUrl.append(code);

        return shortUrl.toString();
    }
}
