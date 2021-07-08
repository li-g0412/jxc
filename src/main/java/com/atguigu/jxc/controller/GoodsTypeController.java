package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

//    查询商品所有分类
//    http://localhost:8080/goodsType/loadGoodsType
    @RequestMapping("/goodsType/loadGoodsType")
    public String loadGoodsType(){
            return goodsTypeService.selectGoodsType();
    }

//    查询所有商品单位
//    http://localhost:8080/unit/list
    @PostMapping("/unit/list")
    public Map<String,Object> goodsList(){
        return goodsTypeService.getAllGoodsList();
    }

//    http://localhost:8080/goodsType/save
    @PostMapping("/goodsType/save")
    public ServiceVO saveGoodsType(String  goodsTypeName,Integer  pId){
        goodsTypeService.insGoodsType(goodsTypeName,pId);
        return new ServiceVO(100,"请求成功");
    }

//    http://localhost:8080/goodsType/delete
    @PostMapping("/goodsType/delete")
    public ServiceVO deleteGoodsType(Integer  goodsTypeId){
        goodsTypeService.delGoodsType(goodsTypeId);
        return new ServiceVO(100,"请求成功");
    }
}
