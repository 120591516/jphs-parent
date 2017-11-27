package com.jinpaihushi.jphs.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;
import com.jinpaihushi.jphs.user.dao.PersonStaticsDao;
import com.jinpaihushi.jphs.user.service.PersonstaticsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.CycleTimeUtils;

/**
 * 注册量统计
 * @author wangwenteng 
 *
 */
@Service("personstaticsService")
public class PersonstaticsServiceImpl extends BaseServiceImpl<StatisticsModel> implements PersonstaticsService {
    @Autowired
    private PersonStaticsDao personStaticsDao;

    @Override
    protected BaseDao<StatisticsModel> getDao() {
        // TODO Auto-generated method stub
        return null;
    }

    private String[] getDeviceNameList(List<StatisticsModel> deviceList) {
        /*for (int i = 0; i < deviceList.size(); i++) {
            for (int j = deviceList.size() - 1; j > i; j--) {
                if (deviceList.get(i).getDevice().equals(deviceList.get(j).getDevice())) {
                    deviceList.remove(j);
                }
            }
        }
        String[] names = new String[deviceList.size()];
        for (int i = 0; i < deviceList.size(); i++) {
            names[i] = "'" + deviceList.get(i).getDevice() + "'";
        }*/
        String[] names = { "'官网'", "'wap站'", "'IOS'", "'安卓'", "'114'", "'微信'", "'呼啦圈'" };
        return names;
    }

    private String[] getNurseDeivce(List<StatisticsModel> deviceList) {
        /*for (int i = 0; i < deviceList.size(); i++) {
            for (int j = deviceList.size() - 1; j > i; j--) {
                if (deviceList.get(i).getDevice().equals(deviceList.get(j).getDevice())) {
                    deviceList.remove(j);
                }
            }
        }
        String[] names = new String[deviceList.size()];
        for (int i = 0; i < deviceList.size(); i++) {
            names[i] = "'" + deviceList.get(i).getDevice() + "'";
        }*/
        String[] names = { "'注册量'", "'审核量'" };
        return names;
    }

    @Override
    public List<Map<String, Object>> userRegisterByWeek(String weeks) {
        String startTime = weeks.split("T")[0];
        String endTime = weeks.split("T")[1];
        List<StatisticsModel> list = personStaticsDao.userRegisterByWeek(startTime, endTime);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            //获取两个日期内的所有日期
            List<String> time = CycleTimeUtils.getDatesBetweenTwoDate(startTime, endTime);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> userRegisterByMonth(String month) {
        List<StatisticsModel> list = personStaticsDao.userRegisterByMonth(month);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(month);
            List<String> time = CycleTimeUtils.getAllDaysMonthByDate(date);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> userRegisterByYear(String year) {
        List<StatisticsModel> list = personStaticsDao.userRegisterByYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            List<String> time = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                String str = "'" + year + "-";
                if (i < 10) {
                    str += "0" + i + "'";
                }
                else {
                    str += i + "'";
                }
                time.add(str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> userAllNumByYear(String year) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        List<StatisticsModel> allNumByYear = personStaticsDao.userAllNumByYear(map);
        Map<String, Object> result = getAnnualData(year, allNumByYear);
        return result;
    }

    @Override
    public List<Map<String, Object>> nurseRegisterByWeek(String weeks, Integer type) {
        String startTime = weeks.split("T")[0];
        String endTime = weeks.split("T")[1];
        Map<String, Object> query = new HashMap<>();
        query.put("startTime", startTime);
        query.put("endTime", endTime);
        query.put("type", type);
        List<StatisticsModel> list = personStaticsDao.nurseRegisterByWeek(query);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            //获取两个日期内的所有日期
            List<String> time = CycleTimeUtils.getDatesBetweenTwoDate(startTime, endTime);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getNurseDeivce(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> nurseRegisterByMonth(String month, Integer type) {
        Map<String, Object> query = new HashMap<>();
        query.put("month", month);
        query.put("type", type);
        List<StatisticsModel> list = personStaticsDao.nurseRegisterByMonth(query);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(month);
            List<String> time = CycleTimeUtils.getAllDaysMonthByDate(date);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getNurseDeivce(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> nurseRegisterByYear(String year, Integer type) {
        Map<String, Object> query = new HashMap<>();
        query.put("year", year);
        query.put("type", type);
        List<StatisticsModel> list = personStaticsDao.nurseRegisterByYear(query);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            List<String> time = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                String str = "'" + year + "-";
                if (i < 10) {
                    str += "0" + i + "'";
                }
                else {
                    str += i + "'";
                }
                time.add(str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getNurseDeivce(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> nurseAllNumByYear(String year, Integer type) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("type", type);
        List<StatisticsModel> allNumByYear = personStaticsDao.nurseAllNumByYear(map);
        Map<String, Object> result = getAnnualData(year, allNumByYear);
        return result;
    }

}
