package com.jinpaihushi.jphs.commoms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.jphs.verification.service.VerificationService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/verification")
public class VerificationController {

	//验证码
    @Value("${SMS_Verification_Code}")
    private String SMS_Verification_Code;
    
    @Autowired
    private DoPostSmsService doPostSmsService;
    
    @Autowired
    private VerificationService verificationService;
	
    /**
     * 判断验证码是否正确
     * 
     * @param hs
     * @param req
     * @param resp
     * @param phone
     * @param validateCode
     * @return
     */
    @RequestMapping(name = "判断验证码是否正确", path = "/ifValidateCode.json")
    @ResponseBody
    public byte[] ifValidateCode(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
            String verificattionCode) {
        try {
        	try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                phone = systemUser.getPhone();
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "regist.ifValidateCode.json 请求参数-phone" + phone + " verificattionCode" + verificattionCode);
            }
            // 查空
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(verificattionCode)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            // 获取该手机号最新一条短信
            Verification vc = verificationService.getLastRecordByPhone(phone);
            if (!verificattionCode.equals(vc.getCode())) {
                // 验证码不对
                return JSONUtil.toJSONResult(0, "验证码不正确", null);
            }
            int t = Common.beforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vc.getValidTime()));
            if (t == 0) {
                // 无效
                return JSONUtil.toJSONResult(0, "验证码已失效", null);
            }
            else if (t == -1) {
                // 失败
                return JSONUtil.toJSONResult(0, "判断验证码有效时间失败", null);
            }

            return JSONUtil.toJSONResult(1, "验证码正确", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("regist.ifValidateCode.json", e);
        }
        return null;
    }
    
	@ResponseBody
    @RequestMapping(name = "发送验证码", path = "/sendMassage.json")
    public byte[] sendMassage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
		String phone = "";
        try {
            try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                phone = systemUser.getPhone();
            }
            catch (Exception e) {
            }
            
            // 查空
            if (phone == null || "".equals(phone)) {
            	return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String regExp = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(phone);
            if (m.equals(false)) {
            	return JSONUtil.toJSONResult(0, "请输入有效的手机号！", null);
            }
            int s = (int) ((Math.random() * 9 + 1) * 1000);// 4位验证码
            // 查询该手机号今天发送验证码的次数
            int size = verificationService.countVerification(phone);
            if (size >= 5) {
            	return JSONUtil.toJSONResult(0, "操作过于频繁，请稍后再试！", null);
            }

            // 发送验证码
            doPostSmsService.sendSms(phone, SMS_Verification_Code, "{\"s\":\"" + s + "\"}");
            Verification vc = new Verification();
            vc.setId(UUIDUtils.getId());
            vc.setPhone(phone);
            Date now = new Date();
            Date now_10 = new Date(now.getTime() + 600000);// 加10分钟前的时间
            vc.setValidTime(now_10);
            vc.setCode(s + "");
            vc.setStatus(0);
            String insert = verificationService.insert(vc);
            if (insert.length() < 0) {
            	return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", vc);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("regist.sendMassage.json,phone=" + phone);
        }
        return null;
    }
	
}
