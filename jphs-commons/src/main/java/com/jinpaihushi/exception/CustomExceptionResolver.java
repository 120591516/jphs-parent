package com.jinpaihushi.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jinpaihushi.util.CustomException;
import com.jinpaihushi.util.SimpleEmail;
import com.jinpaihushi.utils.HttpRequestResolve;
import com.jinpaihushi.utils.Util;

//全局异常处理器  
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Autowired
    private SimpleEmail simpleEmail;

    //系统抛出的异常  
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        //handler就是处理器适配器要执行的Handler对象(只有method)  
        //解析出异常类型。  

        //如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示。  
        CustomException customException = null;
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;

        }
        else {
            //如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）。  
            customException = new CustomException("未知错误");
        }
        //错误信息  
        String message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面  
        modelAndView.addObject("message", message);
        String reqeustUrl = request.getRequestURI();
        String ip = HttpRequestResolve.getIpAddr(request);

        Util.failLog.info("preHandle Request--> URL is " + reqeustUrl + ",parameter is "
                + HttpRequestResolve.getRequestBody(request));
        simpleEmail.sendMail("异常通知",
                "访问地址：" + ip + "\n" + "请求内容：" + "\n" + "preHandle Request--> URL is " + reqeustUrl + ",parameter is "
                        + HttpRequestResolve.getRequestBody(request) + "\n" + "异常信息：" + getEmessage(ex),
                "java@goldnurse.com");
        return null;
    }

    /** 
     * @descript:获得发送者方法的异常信息 
     * 使用字符串作为物理节点的字符输入输出流的用法，即StringReader和StringWriter的用法 
     * PrintWriter(Writer out, boolean autoFlush) 创建带自动行刷新的新 PrintWriter， true代表能自动刷新 
     * @param e 异常信息 
     * @return 
     */
    private static String getEmessage(Exception e) {
        //StringWriter输出异常信息 
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}