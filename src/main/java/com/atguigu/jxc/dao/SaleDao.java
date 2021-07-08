package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.OverflowList;

import java.util.List;

public interface SaleDao {
    void insertDamageList(DamageList damageList);

    void insertDamageListGoods(DamageListGoods listGoods);

    void insertOverflowList(OverflowList overflowList);

    void insertOverflowListGoods(DamageListGoods listGoods);

    List<Goods> selectAlarm(String sTime, String eTime);

    List<Goods> selectAlarmGoods(Integer damageListId);

    List<Goods> selectOverflow(String sTime, String eTime);

    List<Goods> selectOverflowGoods(Integer overflowListId);
}
