package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.service.impl.NewsTypeServiceImpl;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: NewsHeadlineController
 * Package: com.atguigu.headline.controller
 * Description: 门户控制器，不需要增删改的门户页的请求都放在这里
 *
 * @Author Aran
 * @Create 2024/6/10 21:19
 */

@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsTypeService typeService = new NewsTypeServiceImpl();

    /**
     * 查询所有新闻类型业务的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = typeService.findAll();
        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}
