package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.OverflowList;

import java.util.Map;

public interface SaleService {
    void saveDamageList(String damageNumber, DamageList damageList, String damageListGoodsStr);

    void saveOverflowListGoods(String overflowNumber, OverflowList overflowList, String overflowListGoodsStr);

    Map<String, Object> damageListGoods(String sTime, String eTime);

    Map<String, Object> damageGoods(Integer damageListId);

    Map<String, Object> overflowList(String sTime, String eTime);

    Map<String, Object> overflowListGoods(Integer overflowListId);

}
