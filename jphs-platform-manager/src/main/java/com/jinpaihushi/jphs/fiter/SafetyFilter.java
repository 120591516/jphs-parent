package com.jinpaihushi.jphs.fiter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.location.service.LocationService;
import com.jinpaihushi.jphs.platform.model.PlatformUser;
import com.jinpaihushi.utils.HttpRequestResolve;
import com.jinpaihushi.utils.Util;

public class SafetyFilter extends HandlerInterceptorAdapter {

    /**
     * 请求地址
     */
    private String reqeustUrl;

    @Autowired
    private LocationService locationService;

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag = false;
        Util.failLog.info("preHandle......");
        reqeustUrl = request.getRequestURI();
        String ip = HttpRequestResolve.getIpAddr(request);
        System.out.println(ip);
        Util.failLog.info("preHandle Request--> URL is " + reqeustUrl + ",parameter is "
                + HttpRequestResolve.getRequestBody(request));
        //        logger.info(" preHandle Request--> URL is " + reqeustUrl + ",parameter is "
        //                + HttpRequestResolve.getRequestBody(request));
        // 获取当前请求的url
        String url = request.getRequestURI();
        // 登录如果不过滤
        if (url.equals("/login.jhtml")) {
            //如果是登录将省市区的信息放到session中
            List<Location> list = locationService.list(null);
            request.getSession().setAttribute("location", list);
            return true;
        }
        else if (url.equals("/loginOut.jhtml")) {
            return true;
        }
        else {
            if ((PlatformUser) request.getSession().getAttribute("session_user") != null) {
                List<String> list = (List<String>) request.getSession().getAttribute("session_url");
                if (url.endsWith(".json"))
                    return true;
                if (url.equals("/welcome/index.jhtml")) {

                    return true;
                }
                if (url.equals("/upload/index.jhtml")) {
                    return true;
                }
                if (list != null) {
                    if (list.contains(url)) {
                        flag = true;
                    }
                    else {
                        response.sendRedirect("/");
                    }
                }
                else {
                    response.sendRedirect("/");
                }
            }
            else {
                response.sendRedirect("/");
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /*
     * public void doFilterInternal(ServletRequest req, ServletResponse res,
     * FilterChain chain) throws IOException, ServletException {
     * HttpServletRequest request = (HttpServletRequest) req; // 获取当前请求的url
     * String url = request.getRequestURI(); System.out.println(url); //
     * 获取用户的可用的url List<String> list = (List<String>)
     * request.getSession().getAttribute("session_url"); if (list.contains(url))
     * { chain.doFilter(req, res); } else { super.doFilter(request, res, chain);
     * } }
     */

}