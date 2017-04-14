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
public class EncodeFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(EncodeFilter.class);

    private String encode = "utf-8";

    public void destroy() {


    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest hreq = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        hreq.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset="+encode);
        HttpRequestWapper req = new HttpRequestWapper(hreq);
        arg2.doFilter(req, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public class HttpRequestWapper extends HttpServletRequestWrapper {
        // 使用一个组合把相应的数据组合进来
        private HttpServletRequest request;
        public HttpRequestWapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        //重写方法
        @Override
        public String getParameter(String name){
            String str = null;
            str = encoding(request.getParameter(name));

            return super.getParameter(name);
        }
        /*@Override
        public String[] getParameterValues(String name){
            String[] str = null;
            str = ((HttpServletRequest)this.getRequest()).getParameterValues(name);
            if(str != null){
                for(int i = 0; i < str.length; i++){
                    str[i] = encoding(str[i]);
                }
            }
            return str;
        }*/

    }
    //实现转码
    public String encoding(String str){
        if(str != null){
            try {
                str = new String(str.getBytes("iso-8859-1"),this.encode);
            } catch (UnsupportedEncodingException e) {
                log.error("转码异常:{}",e.getMessage(),e);
            }
        }
        return str;
    }


}
