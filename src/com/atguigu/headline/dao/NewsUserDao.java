package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsUser;

/**
 * ClassName: NewsUserDao
 * Package: com.atguigu.headline.dao
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public interface NewsUserDao {
    /**
     * 根据用户名查找同名的user对象
     * @param username
     * @return 查找到的user对象
     */
    NewsUser findByUsername(String username);

    /**
     * 根据uid查找用户
     * @param userId
     * @return 返回用户对象
     */
    NewsUser findByUid(Integer userId);
}
