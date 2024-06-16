package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

/**
 * ClassName: NewsUserDao
 * Package: com.atguigu.headline.dao
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public interface NewsHeadlineDao {
    /**
     * 根据传入的HeadlineQueryVo对象的属性查询数据库，返回的值是HeadlinePageVo对象的集合
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据传入的HeadlineQueryVo对象的属性查询数据库，得到查询到的数据数目
     * @param headlineQueryVo
     * @return
     */
    int toTalSizeNum(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据传入的uid，返回对应的新闻详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHealineDetail(String hid);

    /**
     * 增加文章浏览量
     * @param hid
     */
    void addpageViews(String hid);

    /**
     * 将传进来的新闻headline对象存进数据库
     * @param newsHeadline
     * @return
     */
    int addNewHeadline(NewsHeadline newsHeadline);
}
