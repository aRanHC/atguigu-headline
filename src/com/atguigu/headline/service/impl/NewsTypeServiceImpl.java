package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsTypeDao;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsTypeDaoImpl;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.service.NewsTypeService;

import java.util.List;

/**
 * ClassName: NewsUserServiceImpl
 * Package: com.atguigu.headline.service.impl
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:55
 */
public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}
