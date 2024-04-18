package com.itheima.reggie.filter;

import com.alibaba.fastjson2.JSON;
import com.itheima.reggie.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GRS
 * @since 2024/4/18 下午11:20
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter
{
    private static final AntPathMatcher PATH_MATCHER =
        new AntPathMatcher();

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain
    ) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取本次请求的URI
        String requestURI = request.getRequestURI();

        // 直接放行登录和注册的请求
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/employee/login");
        whiteList.add("/employee/logout");
        whiteList.add("/backend/**");
        whiteList.add("/front/**");

        // 2. 判断本次请求是否需要处理
        boolean check = check(whiteList, requestURI);

        // 3. 如果不需要处理，则直接放行
        if (check)
        {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. 判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        // 5. 如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     */
    private boolean check(List<String> urlList, String requestUri)
    {
        for (String url : urlList)
        {
            boolean match = PATH_MATCHER.match(url, requestUri);
            if (match) return true;
        }
        return false;
    }
}