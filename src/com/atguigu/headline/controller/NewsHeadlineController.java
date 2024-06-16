package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.service.impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: NewsHeadlineController
 * Package: com.atguigu.headline.controller
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/10 21:19
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    /**
     * 发布头条接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        //从token中解析出uid
        Long userId = JwtHelper.getUserId(token);
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        //将解析出来的uid放进newsHeadline的publisher里
        newsHeadline.setPublisher(userId.intValue());
        //将信息存入数据库
        headlineService.addNewHeadline(newsHeadline);

        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 新闻数据修改页面的数据回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline newsHeadline = headlineService.findByHid(hid);
        Map headline = new HashMap();
        headline.put("headline",newsHeadline);
        WebUtil.writeJson(resp,Result.ok(headline));
    }

    /**
     * 提交修改后的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收前端的请求参数
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.updateHeadline(headline);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    /**
     * 根据hid删除新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        headlineService.removeByHid(hid);
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
