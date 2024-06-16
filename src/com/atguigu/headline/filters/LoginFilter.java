package com.atguigu.headline.filters;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: LoginFilter
 * Package: com.atguigu.headline.filters
 * Description:
 *
 * @Author Aran
 * @Create 2024/6/16 18:23
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String token = req.getHeader("token");
        boolean flag = false;
        if (null != token && !JwtHelper.isExpiration(token)) {
            flag = true;
        }
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            WebUtil.writeJson(resp, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}
