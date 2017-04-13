package com.shmily.filter;

/**
 * Created by Administrator on 2017/4/13.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 字符编码过滤器
 * @author
 *
 */
public class CharacterFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(CharacterFilter.class);

    public void destroy() {
        // TODO Auto-generated method stub
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //解决post方式的乱码问题
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

    /**
     * 实现与被增强对象相同的接口
     * 维护一个对被增强对象的引用
     * 覆盖需要被增强的方法
     * 用myRequest类 解决get方式的乱码问题
     */
    class MyRequest extends HttpServletRequestWrapper {
        // 使用一个组合把相应的数据组合进来
        private HttpServletRequest request;
        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        // 在这个地方覆盖一个getparamtert的方法
        @Override
        public String getParameter(String name) {
            // 判断一下是否是get请求
            if (request.getMethod().equalsIgnoreCase("get"));
                String value = request.getParameter(name);
            try {
                value = new String(value.getBytes("iso8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("转码错误{}",e.getMessage(),e);
            }
            return super.getParameter(name);
        }
    }

}
