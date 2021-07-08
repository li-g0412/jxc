package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.Goods;

import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();


    Map<String, Object> getGoodsByPage(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    Map<String, Object> getNoInventoryQuantityMap(Integer page, Integer rows, String nameOrCode);

    Map<String, Object> getHasInventoryQuantityMap(Integer page, Integer rows, String nameOrCode);

    void saveStockById(Integer goodsId, Integer inventoryQuantity, double purchasingPrice);

    int deleteStockById(Integer goodsId);

    void damageListGoods(String damageNumber, DamageList damageList, String damageListGoodsStr);

    Map<String, Object> getAllSupplierList(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    void add(Goods goods);

    void update(Goods goods);

    void delete(Integer goodsId);

    Map<String, Object> getListAlarm();

}
