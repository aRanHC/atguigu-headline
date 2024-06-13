package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsUserDao
 * Package: com.atguigu.headline.dao
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public interface NewsTypeDao {
    /**
     * 查询所有头条分类
     * @return 返回多个头条数据，以List<NewsType>类型返回。
     */
    List<NewsType> findAll();
}
