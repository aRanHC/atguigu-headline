package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

/**
 * ClassName: NewsUserService
 * Package: com.atguigu.headline.service
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:54
 */
public interface NewsUserService {
    /**
     * 根据前端返回账户信息中的账户名查找数据库中是否有同名的账户
     * @param username 前端输入的用户名
     * @return 同名的账户user对象
     */
    NewsUser findByUsername(String username);

    /**
     * 根据uid查找用户
     * @param userId
     * @return 返回找到的对象
     */
    NewsUser findByUid(Integer userId);
}
