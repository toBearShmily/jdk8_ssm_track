package com.shmily.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/20.
 */
public class CorsFilter implements Filter {

    /*以上CorsFilter将从web.xml中读取相关Filter初始化参数，并将在处理HTTP请求时将这些参数写入对应的CORS响应头中，下面大致描述一下这些CORS响应头的意义：
    Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制。
    Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS。
    Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
    Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type。
    Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割。
    需要注意的是，CORS规范中定义Access-Control-Allow-Origin只允许两种取值，要么为*，要么为具体的域名，也就是说，不支持同时配置多个域名。
    为了解决跨多个域的问题，需要在代码中做一些处理，这里将Filter初始化参数作为一个域名的集合（用逗号分隔），只需从当前请求中获取Origin请求头，
    就知道是从哪个域中发出的请求，若该请求在以上允许的域名集合中，则将其放入Access-Control-Allow-Origin响应头，这样跨多个域的问题就轻松解决了*/

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader( "Access-Control-Allow-Origin", request.getHeader("Origin") );
        response.addHeader( "Access-Control-Allow-Credentials", "true" );
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS" );
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    }
}
