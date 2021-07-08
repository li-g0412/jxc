package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleservice;

//    http://localhost:8080/damageListGoods/save?damageNumber=BS1605766644460
    @PostMapping("/damageListGoods/save")
    public ServiceVO saveDamageListGoods(String damageNumber,DamageList damageList, String damageListGoodsStr){
        saleservice.saveDamageList(damageNumber,damageList,damageListGoodsStr);
        return new ServiceVO(100,"操作成功");
    }

//    http://localhost:8080/overflowListGoods/save?overflowNumber=BY1605767033015
    @PostMapping("/overflowListGoods/save")
    public ServiceVO saveOverflowList(String overflowNumber, OverflowList overflowList, String overflowListGoodsStr){
        saleservice.saveOverflowListGoods(overflowNumber,overflowList,overflowListGoodsStr);
        return new ServiceVO(100,"操作成功");
    }

//    http://localhost:8080/damageListGoods/list
    @PostMapping("/damageListGoods/list")
    public Map<String,Object> damageListGoods(String  sTime, String  eTime){
        return saleservice.damageListGoods(sTime,eTime);
    }

//    http://localhost:8080/damageListGoods/goodsList
    @PostMapping("/damageListGoods/goodsList")
    public Map<String,Object> damageGoods(Integer damageListId){
        return saleservice.damageGoods(damageListId);
    }

//    http://localhost:8080/overflowListGoods/list
    @PostMapping("/overflowListGoods/list")
    public Map<String,Object> overflowList(String  sTime, String  eTime){
        return saleservice.overflowList(sTime,eTime);
    }

//    http://localhost:8080/overflowListGoods/goodsList
    @PostMapping("/overflowListGoods/goodsList")
    public Map<String,Object> overflowListGoods(Integer overflowListId){
        return saleservice.overflowListGoods(overflowListId);
    }
}
