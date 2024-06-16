package com.atguigu.headline.test;

import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsHeadlineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ClassName: BaseDaoTest
 * Package: com.atguigu.headline.test
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/14 12:52
 */
public class TestBaseDao {
    BaseDao baseDao = new BaseDao();
    NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();
    @Test
    public void testUpdate (){
        String sql = """
                    insert into news_user values (DEFAULT,?,?,?)
                """;
        int row =  baseDao.baseUpdate(sql,"dongxu","123456","张三");
        System.out.println(row);
    }
    @Test
    public void testBaseQuery(){
        String sql = "select tid,tname from news_type";
        List<NewsType> allNewsType = baseDao.baseQuery(NewsType.class, sql);
        System.out.println(allNewsType);
        String sql2 = " SELECT * FROM news_user WHERE uid = ?";
        NewsUser user = baseDao.baseQueryObject(NewsUser.class, sql2,1);
        System.out.println(user);

    }
    @Test
    public void testFindPage(){
        HeadlineQueryVo headlineQueryVo = new HeadlineQueryVo("端",0,1,10);
        List<HeadlinePageVo> page = newsHeadlineDao.findPage(headlineQueryVo);
        System.out.println(page);
    }
}
