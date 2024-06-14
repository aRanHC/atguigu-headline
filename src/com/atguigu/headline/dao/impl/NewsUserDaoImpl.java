package com.atguigu.headline.dao.impl;

import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.pojo.NewsUser;

import java.util.List;

/**
 * ClassName: NewsUserDaoImpl
 * Package: com.atguigu.headline.dao.impl
 * Description: 接口实现类
 *
 * @Author Aran
 * @Create 2024/6/10 20:51
 */
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        String sql = "select uid,username,user_pwd userPwd, nick_name nickName from news_user where username = ? ";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        return newsUserList != null && newsUserList.size()>0 ? newsUserList.get(0) : null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = "select uid,username,user_pwd userPwd, nick_name nickName from news_user where uid = ? ";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        return newsUserList != null && newsUserList.size()>0 ? newsUserList.get(0) : null;

    }

    @Override
    public int registUser(NewsUser newNewsUser) {
        String sql = """
                    insert into news_user values (DEFAULT,?,?,?)
                """;
        int row =  baseUpdate(sql, newNewsUser.getUsername(), newNewsUser.getUserPwd(), newNewsUser.getNickName());
        System.out.println(row);
        return row;
    }
}
