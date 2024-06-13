package com.atguigu.headline.test;

import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.WebUtil;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TestJwtHelper
 * Package: com.atguigu.headline.test
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/12 22:05
 */
public class TestJwtHelper {
    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1l);
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);
        System.out.println(JwtHelper.isExpiration(token));
        Thread.sleep(6000);
        System.out.println(JwtHelper.isExpiration(token));
    }
}
