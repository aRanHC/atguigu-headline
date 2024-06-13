package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsUserService
 * Package: com.atguigu.headline.service
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:54
 */
public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 返回多个头条，以List<NewsType>集合类型返回
     */
    List<NewsType> findAll();
}
