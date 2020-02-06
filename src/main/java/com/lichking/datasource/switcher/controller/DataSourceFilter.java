package com.lichking.datasource.switcher.controller;

import com.lichking.datasource.switcher.route.DataSourceContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DataSourceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String key = req.getHeader("key");
        DataSourceContext.set(key);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //
    }
}
