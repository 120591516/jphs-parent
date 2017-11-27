package com.jinpaihushi.jphs.nurse.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseStaticsDao;
import com.jinpaihushi.jphs.nurse.service.NurseStaticsService;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.MyPredicate;
import com.jinpaihushi.utils.PageInfos;

@Service("nurseStaticsService")
public class NurseStaticsServiceImpl extends BaseServiceImpl<BaseModel> implements NurseStaticsService {
    @Autowired
    private NurseStaticsDao nurseStaticsDao;

    private DecimalFormat df = new DecimalFormat("0.00%");;

    @Override
    protected BaseDao<BaseModel> getDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> nurseJob = nurseStaticsDao.getNurseJob();
        List<Map<String, Object>> nurseAddress = nurseStaticsDao.getNurseAddress();
        List<Map<String, Object>> nurseAge = nurseStaticsDao.getNurseAge();
        List<Map<String, Object>> nurseJobtitle = nurseStaticsDao.getNurseJobtitle();
        Map<String, Object> changeJob = changeMapData(nurseJob, "jobTitles", "nurseJob", "nurseJobValue");
        Map<String, Object> changeAddress = changeMapData(nurseAddress, "address", "nurseAddress", "nurseAddressValue");
        Map<String, Object> changeAge = changeMapData(nurseAge, "ages", "nurseAge", "nurseAgeValue");
        Map<String, Object> changeJobtitle = changeMapData(nurseJobtitle, "jobtitleName", "nurseJobtitle",
                "nurseJobtitleValue");
        result.put("changeJob", changeJob);
        result.put("changeAddress", changeAddress);
        result.put("changeAge", changeAge);
        result.put("changeJobtitle", changeJobtitle);
        return result;
    }

    /**
     * 处理饼图数据
     * @param nurseJob 饼图数据
     * @param keyName dao层返回的keyName
     * @param titleDataName 处理之后的数据名称
     * @param titleValueName 值得key值
     * @return
     */
    private Map<String, Object> changeMapData(List<Map<String, Object>> nurseJob, String keyName, String titleDataName,
            String titleValueName) {
        Map<String, Object> result = new HashMap<>();
        String[] jobTitle = new String[nurseJob.size()];
        int[] jobtitleValue = new int[nurseJob.size()];
        for (int i = 0; i < nurseJob.size(); i++) {
            jobTitle[i] = "'" + nurseJob.get(i).get(keyName).toString() + "'";
            jobtitleValue[i] = Integer.parseInt(nurseJob.get(i).get("total").toString());
        }
        result.put(titleDataName, Arrays.toString(jobTitle));
        result.put(titleValueName, Arrays.toString(jobtitleValue));
        return result;
    }

    @Override
    public Map<String, Object> getNurseServerTop(String year) {
        List<Map<String, Object>> list = nurseStaticsDao.getNurseServerTop(year);
        Map<String, Object> barData = changeBarData(list, "title", "total");
        return barData;
    }

    @Override
    public List<Map<String, Object>> getServerTotal(String year) {
        List<Map<String, Object>> total = nurseStaticsDao.getServerTotal(year);
        return total;
    }

    @Override
    public Map<String, Object> getNurseOrderTop(Map<String, Object> query) {
        List<Map<String, Object>> list = nurseStaticsDao.getNurseOrderTop(query);
        Map<String, Object> barData = changeBarData(list, "nurseName", "total");
        return barData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getNurseOrderDetail(HttpServletRequest request, String year, int p, int n) {
        Map<String, Object> resultMap = new HashMap<>();
        nurseStaticsDao.setRank();
        List<Map<String, Object>> result = new ArrayList<>();
        PageHelper.startPage(p, n);
        List<Map<String, Object>> list = nurseStaticsDao.getNurseOrderDetail(year);
        PageInfos<Map<String, Object>> page = new PageInfos<>(list, request);
        List<Map<String, Object>> cityList = new ArrayList<>(list);
        Map<String, Object> map = null;
        for (int i = 0; i < cityList.size(); i++) {
            for (int j = cityList.size() - 1; j > i; j--) {
                if (cityList.get(i).get("addressN").equals(cityList.get(j).get("addressN"))) {
                    cityList.remove(j);
                }
            }
        }
        for (int i = 0; i < cityList.size(); i++) {
            Predicate platPredicate = new MyPredicate("addressN", cityList.get(i).get("addressN"));
            List<Map<String, Object>> citySelect = (List<Map<String, Object>>) CollectionUtils.select(list,
                    platPredicate);
            for (int j = 0; j < citySelect.size(); j++) {
                map = citySelect.get(j);
                Double cancelNum = Double.parseDouble(map.get("cancelNum").toString());
                Double total = Double.parseDouble(map.get("total").toString());
                Double cancelpPercent = DoubleUtils.div(cancelNum, DoubleUtils.add(cancelNum, total), 2);
                map.put("cityRank", j + 1);
                map.put("cancelpPercent", df.format(cancelpPercent));
                result.add(map);
            }
        }
        resultMap.put("list", result);
        resultMap.put("pageInfo", page);
        return resultMap;
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00%");
        System.out.println(df.format(0.1234));
    }
}
