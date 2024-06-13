package com.atguigu.headline.dao.impl;

import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsTypeDao;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsUserDaoImpl
 * Package: com.atguigu.headline.dao.impl
 * Description: 接口实现类
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql = "select tid,tname from news_type";
        return baseQuery(NewsType.class,sql);
    }
}
