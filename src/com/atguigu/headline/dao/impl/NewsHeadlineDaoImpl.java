package com.atguigu.headline.dao.impl;

import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsHeadlineDao;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: NewsUserDaoImpl
 * Package: com.atguigu.headline.dao.impl
 * Description: 接口实现类
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    /**
     * private Integer hid;
     * private String title;
     * private Integer type;
     * private Integer pageViews;
     * private Long pastHours;
     * private Integer publisher;
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public List<HeadlinePageVo> findPage(HeadlineQueryVo headlineQueryVo) {
        List parms = new ArrayList();
        String sql = """
                    select
                        hid,
                        title,
                        type,
                        page_views pageViews,
                        TIMESTAMPDIFF(HOUR,create_time,now()) pastHours,
                        publisher
                    from
                        news_headline
                    where
                        is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            parms.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            parms.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        sql = sql.concat(" order by pastHours asc ,page_views desc  ");
        sql = sql.concat(" limit ? , ? "); //第一个放前面已经过去多少条数据，第二个放这页应该放多少条
        parms.add(headlineQueryVo.getPageSize() * (headlineQueryVo.getPageNum() - 1));
        parms.add(headlineQueryVo.getPageSize());
        List<HeadlinePageVo> pageData = baseQuery(HeadlinePageVo.class, sql, parms.toArray());
        return pageData;
    }

    @Override
    public int toTalSizeNum(HeadlineQueryVo headlineQueryVo) {
        List parms = new ArrayList();
        String sql = """
                    select
                        count(1)
                    from
                        news_headline
                    where
                        is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            parms.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            parms.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        Long l = baseQueryObject(Long.class, sql, parms.toArray());
        return l.intValue();
    }

    @Override
    public void addpageViews(String hid) {
        int hidInt = Integer.parseInt(hid);
        String sql = "update news_headline set page_views = page_views + 1 where hid = ?";
        int i = baseUpdate(sql, hidInt);
    }

    @Override
    public HeadlineDetailVo findHealineDetail(String hid) {
        int hidInt = Integer.parseInt(hid);
        String sql = """
                        select
                            h.hid hid,
                            h.title title,
                            h.article article,
                            h.type type,
                            t.tname typeName,
                            h.page_views pageViews,
                            timestampdiff(hour,h.create_time,now()) pastHours,
                            h.publisher publisher,
                            u.nick_name author
                        from
                            news_headline h
                            left join
                                news_type t
                            on
                                h.type = t.tid
                            left join
                                news_user u
                            on
                            h.publisher = u.uid
                        
                        where
                            h.hid = ?
                """;
        List<HeadlineDetailVo> headlineDetailVos = baseQuery(HeadlineDetailVo.class, sql, hid);
        if (null != headlineDetailVos && headlineDetailVos.size() > 0) {
            return headlineDetailVos.get(0);
        }
        return null;
    }

    @Override
    public int addNewHeadline(NewsHeadline newsHeadline) {
        String sql = "insert into news_headline values (default,?,?,?,?,0,now(),now(),0)";
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());

    }

    @Override
    public NewsHeadline findByHid(int hid) {
        String sql =
                """
                    select hid ,title,article,type from news_headline where hid = ?;
                """;
        List<NewsHeadline> newsHeadlines = baseQuery(NewsHeadline.class, sql, hid);
        if (null != newsHeadlines && newsHeadlines.size() > 0) {
            return newsHeadlines.get(0);
        }else
            return null;
    }

    @Override
    public void updateHeadline(NewsHeadline headline) {
        String sql = "update news_headline set title = ? ,article = ?, type = ?,update_time=now() where hid = ?";
        baseUpdate(sql,headline.getTitle(),headline.getArticle(),headline.getType(),headline.getHid());
    }

    @Override
    public void removeByHid(Integer hid) {
        String sql = "update news_headline set is_deleted = 1 where hid = ?";
        baseUpdate(sql,hid);
    }
}
