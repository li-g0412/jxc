package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1 68/xxxxxx
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for(int i = 4;i > intCode.toString().length();i--){

            unitCode = "0"+unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }


    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */

    @Override
    public Map<String, Object> getGoodsByPage(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Goods> stockList = goodsDao.getAllGoods(page,rows,codeOrName,goodsTypeId);
        map.put("rows",stockList);
        map.put("total",stockList.size());
        try {
            int a  = 1/0;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return map;
    }

    //分页查询无库存商品信息
    @Override
    public Map<String, Object> getNoInventoryQuantityMap(Integer page, Integer rows, String nameOrCode) {
        HashMap<String, Object> goodsTypeMap = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Goods> stockList = goodsDao.getAllGoodsType(page,rows,nameOrCode);

        goodsTypeMap.put("total",stockList.size());
        goodsTypeMap.put("rows",stockList);
        return goodsTypeMap;
    }

    //分页查询有库存商品信息
    @Override
    public Map<String, Object> getHasInventoryQuantityMap(Integer page, Integer rows, String nameOrCode) {
        HashMap<String, Object> hasInventoryMap = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Goods> stockList = goodsDao.getHasInventoryMap(page,rows,nameOrCode);

        hasInventoryMap.put("total",stockList.size());
        hasInventoryMap.put("rows",stockList);
        return hasInventoryMap;
    }

    @Override
    public void saveStockById(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        goodsDao.saveStock(goodsId,inventoryQuantity,purchasingPrice);
    }

    @Override
    public int deleteStockById(Integer goodsId) {
        return goodsDao.delStockById(goodsId);
    }

    @Override
    public void damageListGoods(String damageNumber, DamageList damageList, String damageListGoodsStr) {
    }

    @Override
    public Map<String, Object> getAllSupplierList(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Supplier> supplierList = goodsDao.getAllSupplier(page,rows,goodsName,goodsTypeId);
        map.put("rows",supplierList);
        map.put("total",supplierList.size());
        return map;
    }

    @Override
    public void add(Goods goods) {
        goods.setInventoryQuantity(100);
        goods.setState(0);
        goodsDao.add(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void delete(Integer goodsId) {
        goodsDao.delete(goodsId);
    }

    @Override
    public Map<String, Object> getListAlarm() {
        HashMap<String, Object> map = new HashMap<>();
        List<Goods> alarmList = goodsDao.selectAlarmList();
        map.put("rows",alarmList);
        return map;
    }


}
