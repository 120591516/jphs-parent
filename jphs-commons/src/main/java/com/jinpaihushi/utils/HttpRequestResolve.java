package com.jinpaihushi.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestResolve {
    private static Logger logger = Logger.getRootLogger();

    public static String getResponseBody(HttpServletResponse response) {
        String result = new String();
        try {
            result = response.getOutputStream().toString();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析请求实体信息
     * 
     * @param request
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static String getRequestBody(HttpServletRequest request) {
        Enumeration enu = request.getParameterNames();
        String paraName = "";
        JSONObject jsonObj = new JSONObject();
        while (enu.hasMoreElements()) {
            paraName = (String) enu.nextElement();
            logger.info(paraName + ": " + request.getParameter(paraName));
            jsonObj.put(paraName, request.getParameter(paraName));
        }
        Enumeration attrs = null;
        attrs = request.getHeaderNames();
        while (attrs.hasMoreElements()) {
            String attr = (String) attrs.nextElement();
            logger.info(attr + ": " + request.getHeader(attr));
            jsonObj.put(attr, request.getHeader(attr));
        }
        return jsonObj.toString();
    }

    /**
     * 获取客户端的IP
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /** 
    * @Title: getDomainName 
    * @Description: TODO
    * @param @param request
    * @param @return    
    * @return String    
    * @throws 
    */
    /**
     * 获取域名
     */
    public static String getDomainName(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/")
                .toString();
        return tempContextUrl;
    }
}