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
}
