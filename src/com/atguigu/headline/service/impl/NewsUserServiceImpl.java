package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsUserDaoImpl;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;

/**
 * ClassName: NewsUserServiceImpl
 * Package: com.atguigu.headline.service.impl
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:55
 */
public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        NewsUser loginUser = userDao.findByUsername(username);
        return loginUser;
    }
}
