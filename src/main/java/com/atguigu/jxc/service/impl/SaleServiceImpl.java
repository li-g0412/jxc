package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.SaleService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDao saleDao;

    @Override
    public void saveDamageList(String damageNumber, DamageList damageList, String damageListGoodsStr) {
        damageList.setDamageNumber(damageNumber);
        damageList.setUserId(1);
        saleDao.insertDamageList(damageList);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(damageListGoodsStr);
        JsonArray asJsonArray = parse.getAsJsonArray();
        Iterator it = asJsonArray.iterator();
        while (it.hasNext()){
            parse = (JsonElement) it.next();
            String json = parse.toString();
            DamageListGoods listGoods = gson.fromJson(json, DamageListGoods.class);
            listGoods.setDamageListId(damageList.getDamageListId());
            saleDao.insertDamageListGoods(listGoods);
        }
    }

    @Override
    public void saveOverflowListGoods(String overflowNumber, OverflowList overflowList, String overflowListGoodsStr) {
        overflowList.setOverflowNumber(overflowNumber);
        overflowList.setUserId(1);
        saleDao.insertOverflowList(overflowList);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(overflowListGoodsStr);
        JsonArray asJsonArray = parse.getAsJsonArray();
        Iterator it = asJsonArray.iterator();
        while (it.hasNext()){
            parse = (JsonElement) it.next();
            String json = parse.toString();
            DamageListGoods listGoods = gson.fromJson(json, DamageListGoods.class);
            listGoods.setDamageListId(overflowList.getOverflowListId());
            saleDao.insertOverflowListGoods(listGoods);
        }
    }

    @Override
    public Map<String, Object> damageListGoods(String sTime, String eTime) {
        HashMap<String, Object> map = new HashMap<>();
        List<Goods> alarmList = saleDao.selectAlarm(sTime,eTime);
        map.put("rows",alarmList);
        return map;
    }

    @Override
    public Map<String, Object> damageGoods(Integer damageListId) {
        HashMap<String, Object> map = new HashMap<>();
        List<Goods> alarmGoodsList = saleDao.selectAlarmGoods(damageListId);
        map.put("rows",alarmGoodsList);
        return map;
    }

    @Override
    public Map<String, Object> overflowList(String sTime, String eTime) {
        HashMap<String, Object> map = new HashMap<>();
        List<Goods> alarmList = saleDao.selectOverflow(sTime,eTime);
        map.put("rows",alarmList);
        return map;
    }

    @Override
    public Map<String, Object> overflowListGoods(Integer overflowListId) {
        HashMap<String, Object> map = new HashMap<>();
        List<Goods> alarmGoodsList = saleDao.selectOverflowGoods(overflowListId);
        map.put("rows",alarmGoodsList);
        return map;
    }
}
