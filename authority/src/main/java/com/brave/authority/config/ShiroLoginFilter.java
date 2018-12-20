package com.brave.authority.config;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "shiroLoginFilter")
public class ShiroLoginFilter  implements Filter {

    private FilterConfig config = null;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
    @Override
    public void destroy() {
        this.config = null;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 允许哪些Origin发起跨域请求,nginx下正常
        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
        response.setHeader( "Access-Control-Allow-Origin", "*" );
        // 允许请求的方法
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader( "Access-Control-Max-Age", "3600" );
        // 表明它允许跨域请求包含xxx头
        response.setHeader( "Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept,Authorization" );
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        // response.setHeader( "Access-Control-Expose-Headers", "*" );
        /*
        * 但在前端调用时，进行options预检时，报以下错误
            Failed to load http://127.0.0.1:9080/getUserInfo: Response for preflight is invalid (redirect)
            经分析，报错原因是进行预检时，没有带上token，倒置会重定向到unauth路径中，所以进行调用时，会报如下错误。
             解决办法是通过过滤器，如果判断请求为OPTIONS预检时，直接返回成功
        * */
        if (request.getMethod().equals( "OPTIONS" )) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;

        }
        filterChain.doFilter( servletRequest, response );
    }


}