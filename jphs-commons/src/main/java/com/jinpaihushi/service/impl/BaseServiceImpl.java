package com.jinpaihushi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import com.github.pagehelper.Page;
import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.service.ReadonlyService;
import com.jinpaihushi.utils.IntToSmallChineseNumber;
import com.jinpaihushi.utils.MyPredicate;
import com.jinpaihushi.utils.exception.ExceptionHandler;

public abstract class BaseServiceImpl<T extends BaseModel>
        implements ReadonlyService<T>, BaseService<T>, ApplicationEventPublisher {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ExceptionHandler exceptionHandler;

    public BaseServiceImpl() {
        super();
        logger.info("init:" + this.getClass().getSimpleName());
    }

    protected abstract BaseDao<T> getDao();

    protected void defaultModel(T model) {
        if (model.getStatus() == null) {
            model.setStatus(0);
        }
        if (model.getType() == null) {
            model.setType(0);
        }
        if (model.getCreateTime() == null) {
            model.setCreateTime(new Date());
        }
        if (model.getUpdateTime() == null) {
            model.setUpdateTime(new Date());
        }
    }

    @Override
    public String insert(T model) {
        defaultModel(model);
        int result = getDao().insert(model);
        if (result != 1) {
            throw new RuntimeException("数据插入失败");
        }
        return model.getId();
    }

    @Override
    public int count(T model) {
        return getDao().count(model);
    }

    @Override
    public Page<T> query(T model, RowBounds row) {
        return getDao().query(model, row);
    }

    @Override
    public List<T> list(T model) {
        return getDao().list(model);
    }

    @Override
    public T loadById(String id) {
        return getDao().loadById(id);
    }

    @Override
    public boolean update(T model) {
        model.setUpdateTime(new Date());
        return getDao().update(model) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return getDao().deleteById(id) > 0;
    }

    @Override
    public boolean disableById(String id) {
        return getDao().disableById(id) > 0;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        SpringHelper.context.publishEvent(event);
    }

    @Override
    public void publishEvent(Object event) {
        SpringHelper.context.publishEvent(event);
    }

    @Override
    public Page<T> query(T model) {
        return getDao().query(model);
    }

    @Override
    public T load(T model) {
        return getDao().load(model);
    }

    @Override
    public int inserts(List<T> models) {
        if (CollectionUtils.isEmpty(models))
            return 0;
        for (T model : models) {
            defaultModel(model);
        }
        return getDao().inserts(models);
    }

    @Override
    public boolean save(T model) {
        if (model.getId() == null) {
            this.insert(model);
            return true;
        }
        else {
            return this.update(model);
        }
    }

    /**
     * 统计中处理多种数据
     * @param list
     * @param result
     * @param time
     * @param deviceName
     */
    @SuppressWarnings("unchecked")
    protected void getDviceData(List<StatisticsModel> list, List<Map<String, Object>> result, List<String> time,
            String[] deviceName) {
        Map<String, Object> map;
        List<StatisticsModel> deviceData = null;
        StatisticsModel al = null;
        for (int i = 0; i < deviceName.length; i++) {
            map = new HashMap<>();
            //根据平台的信息取出集合中的结果
            deviceData = new ArrayList<>();
            Predicate platPredicate = new MyPredicate("device", deviceName[i].replaceAll("'", ""));
            List<StatisticsModel> select = (List<StatisticsModel>) CollectionUtils.select(list, platPredicate);
            for (int j = 0; j < time.size(); j++) {
                al = new StatisticsModel();
                for (int k = 0; k < select.size(); k++) {
                    if ((time.get(j).replaceAll("'", "")).equals(select.get(k).getCreate_time())) {
                        deviceData.add(j, select.get(k));
                        select.remove(k);
                    }
                }
                if (deviceData.size() == j) {
                    al.setDevice(deviceName[i].replaceAll("'", ""));
                    al.setCreate_time(time.get(j));
                    al.setNum("0");
                    deviceData.add(j, al);
                }

            }
            map.put(deviceName[i], deviceData);
            result.add(map);

        }
    }

    /**
     * 处理全年的数据统计
     * @param year
     * @param allNumByYear 
     * @return
     */
    protected Map<String, Object> getAnnualData(String year, List<StatisticsModel> allNumByYear) {
        Map<String, Object> result = new HashMap<>();
        String[] month = new String[13];
        String[] chMonth = new String[13];
        for (int i = 0; i < 13; i++) {
            if (i < 10) {
                if (i == 0)
                    month[i] = "  ";
                else
                    month[i] = "0" + i;
            }
            else {
                month[i] = i + "";
            }
            System.out.println();
            chMonth[i] = IntToSmallChineseNumber.ToCH(i);

        }
        result.put("months", chMonth);
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            if ((i + 1) < 10) {
                days[i] = "0" + (i + 1) + "";
            }
            else {
                days[i] = "" + (i + 1) + "";
            }

        }
        result.put("days", Arrays.toString(days));
        String[][] allData = getDataByAllData(allNumByYear, month, chMonth, days);
        result.put("allData", allData);
        result.put("year", "'" + year + "-01'");
        return result;
    }

    @SuppressWarnings("unchecked")
    protected String[][] getDataByAllData(List<StatisticsModel> allNumByYear, String[] month, String[] chMonth,
            String[] days) {
        String[][] allData = new String[days.length][chMonth.length];
        for (int i = 0; i < allData.length; i++) {
            Predicate daysPredicate = new MyPredicate("device", days[i].replace("'", ""));
            List<StatisticsModel> daysSelect = (List<StatisticsModel>) CollectionUtils.select(allNumByYear,
                    daysPredicate);
            for (int j = 0; j < allData[i].length; j++) {
                Predicate monthPredicate = new MyPredicate("months", month[j]);
                List<StatisticsModel> monthSelect = (List<StatisticsModel>) CollectionUtils.select(daysSelect,
                        monthPredicate);
                if (j == 0) {
                    allData[i][j] = days[i];
                }
                else {
                    if (monthSelect.size() == 0) {
                        allData[i][j] = "0";
                    }
                    else {
                        allData[i][j] = monthSelect.get(0).getNum() + "";
                    }
                }
            }
        }
        return allData;
    }

    /**
     * 处理柱状图的数据
     * @param list
     * @param keyTitle
     * @param valueTitle
     * @return
     */
    protected Map<String, Object> changeBarData(List<Map<String, Object>> list, String keyTitle, String valueTitle) {
        String[] xAxisData = new String[list.size()];
        String[] yAxisData = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            xAxisData[i] = "'" + list.get(i).get(keyTitle).toString() + "'";
            yAxisData[i] = list.get(i).get(valueTitle).toString();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("xAxisData", xAxisData);
        result.put("yAxisData", yAxisData);
        return result;
    }
}
