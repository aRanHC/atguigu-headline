package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

/**
 * ClassName: NewsUserService
 * Package: com.atguigu.headline.service
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:54
 */
public interface NewsHeadlineService {
    /**
     * 分页查询，包含的数据有
     *  pageData : 是多个HeadlinePageVo集合
     *  pageNum
     *  pageSize
     *  totalPage
     *  totalSize
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据uid返回新闻详情，并且每次请求pageViews（浏览量）加一
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(String hid);

    /**
     * 新增新闻
     * @param newsHeadline
     */
    int addNewHeadline(NewsHeadline newsHeadline);
}
