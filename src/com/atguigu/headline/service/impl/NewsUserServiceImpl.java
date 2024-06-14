package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsUserDaoImpl;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.util.MD5Util;

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

    @Override
    public NewsUser findByUid(Integer userId) {
        NewsUser newsUser = userDao.findByUid(userId);
        return newsUser;
    }

    @Override
    public boolean isRegist(NewsUser newNewsUser) {
        String encrypt = MD5Util.encrypt(newNewsUser.getUserPwd());
        newNewsUser.setUserPwd(encrypt);
        int rows = userDao.registUser(newNewsUser);
        if (rows == 0){
            return false;
        }
        return true;
    }
}
