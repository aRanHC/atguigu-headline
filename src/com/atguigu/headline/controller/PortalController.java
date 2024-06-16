package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.service.impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.service.impl.NewsTypeServiceImpl;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private NewsHeadlineService newsHeadlineService = new NewsHeadlineServiceImpl();

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

    /**
     * 分页查询头条信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        //将参数传给服务层
        Map pageInfo = newsHeadlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo",pageInfo);
        //将分页查询结果json返回给前端
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 后端根据新闻id查询完整新闻文章信息并返回
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收前端参数
        String hid = req.getParameter("hid");
        //调用service层方法
        HeadlineDetailVo headlineDetail = newsHeadlineService.findHeadlineDetail(hid);
        //将结果保存到data中
        Map headline = new HashMap();
        headline.put("headline",headlineDetail);
        WebUtil.writeJson(resp,Result.ok(headline));
    }
}
