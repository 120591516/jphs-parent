package com.jinpaihushi.jphs.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.site.service.SiteService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(name = "test", path = "/test")
public class TransactionManagerTest {
    @Autowired
    private SiteService siteService;

    @ResponseBody
    @RequestMapping(name = "获取站点列表", path = "/test.json")
    public byte[] getSiteList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 记录日志-debug
        if (Util.debugLog.isDebugEnabled()) {
            Util.debugLog.debug("site.getSiteList.json");
        }
        List<Map<String, Object>> list = siteService.Test();
        // 查空
        // 1.根据 name，password,type查询完整信息
        // 2.错误N种情况判断及返回前端
        // 3.信息无误，封装信息以及生成token，返回前端

        return JSONUtil.toJSONResult(1, "操作成功！", list);
    }
}
