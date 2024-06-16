package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsHeadlineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: NewsUserServiceImpl
 * Package: com.atguigu.headline.service.impl
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 20:55
 */
public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();
    /**
     * pageData : 是多个HeadlinePageVo集合
     * pageNum
     * pageSize
     * totalPage
     * totalSize
     * @param headlineQueryVo
     * @return
     */
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData = newsHeadlineDao.findPage(headlineQueryVo);
        int totalSize = newsHeadlineDao.toTalSizeNum(headlineQueryVo);
        int totalPage = (int)Math.ceil((double)totalSize / pageSize);
        Map result = new HashMap();
        result.put("pageNum",pageNum);
        result.put("pageSize",pageSize);
        result.put("totalSize",totalSize);
        result.put("totalPage",totalPage);
        result.put("pageData",pageData);
        return result;

    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(String hid) {
        newsHeadlineDao.addpageViews(hid);
        HeadlineDetailVo headlineDetailVo = newsHeadlineDao.findHealineDetail(hid);
        return headlineDetailVo;
    }
}
