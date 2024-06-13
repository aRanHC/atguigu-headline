package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.service.impl.NewsUserServiceImpl;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.MD5Util;
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
@WebServlet("/user/*")
public class NewsUserController extends BaseController{

    private NewsUserService userService = new NewsUserServiceImpl();
    /**
     * 处理登录表单提交的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        //调用服务层方法，实现登录
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());
        Result result = null;
        if (null != loginUser){
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())){
                Map data = new HashMap();
                data.put("token",JwtHelper.createToken(loginUser.getUid().longValue()));
//                result = Result.build(data,ResultCodeEnum.SUCCESS);
                result = Result.ok(data);
            }else {
                result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
            }
        }else {
            result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        //向客户端响应登录验证信息
        WebUtil.writeJson(resp,result);
    }

    /**
     *  根据token口令获取用户详细信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        //获取token
        String token = req.getHeader("token");
        if (null != token && (!"".equals(token))){
            if (!JwtHelper.isExpiration(token)){
                Integer userId = JwtHelper.getUserId(token).intValue();
                // TODO 这个service方法还没有完成
                NewsUser newsUser = userService.findByUid(userId);
                if (null != newsUser){
                    Map data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                    result = Result.ok(data);
                }
            }
        }
        WebUtil.writeJson(resp,result);
    }
}
