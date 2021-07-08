package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/goods/listInventory")
    public Map<String,Object> listInventory(Integer page ,Integer rows ,String codeOrName,Integer goodsTypeId){
        return goodsService.getGoodsByPage(page,rows,codeOrName,goodsTypeId);
    }


    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     * http://localhost:8080/goods/list
     * Integer page, Integer rows, String goodsName, Integer goodsTypeId
     * post map
     */
    @PostMapping("/goods/list")
    public Map<String,Object> goodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId){
        return goodsService.getAllSupplierList(page,rows,goodsName,goodsTypeId);
    }


    /**
     * 生成商品编码
     * @return
     */
    @RequestMapping("goods/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     * http://localhost:8080/goods/save?goodsId=37
     */
    @PostMapping("/goods/save")
    public ServiceVO saveOrUpdate(Goods goods, HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        if (goodsId==null){
            goodsService.add(goods);
        }else {
            goodsService.update(goods);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }


    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     * http://localhost:8080/goods/delete
     */
    @PostMapping("/goods/delete")
    public ServiceVO deleteGoods(Integer goodsId){
        goodsService.delete(goodsId);
        return new ServiceVO(100,"请求成功");
    }

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     * http://localhost:8080/goods/getNoInventoryQuantity
     */
    @PostMapping("/goods/getNoInventoryQuantity")
    public Map<String,Object> getNoInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getNoInventoryQuantityMap(page, rows, nameOrCode);
    }


    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("/goods/getHasInventoryQuantity")
    public Map<String,Object> getHasInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getHasInventoryQuantityMap(page, rows, nameOrCode);
    }

    /**
     * 添加商品期初库存
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     * http://localhost:8080/goods/saveStock?goodsId=25
     */
    @PostMapping("/goods/saveStock")
    public ServiceVO saveStock(Integer goodsId,Integer inventoryQuantity,double purchasingPrice){
        goodsService.saveStockById(goodsId,inventoryQuantity,purchasingPrice);
        return new ServiceVO(100,"请求成功");
    }

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     * http://localhost:8080/goods/deleteStock
     */
    @PostMapping("/goods/deleteStock")
    public ServiceVO deleteStock(Integer goodsId){
        int i = goodsService.deleteStockById(goodsId);
        if (i>0){
            return new ServiceVO(100,"请求成功");
        }else {
            return new ServiceVO(500,"入库、有进货和销售单据的不能删除");
        }
    }

    /**
     * 查询库存报警商品信息
     * @return
     * http://localhost:8080/goods/listAlarm
     */
    @PostMapping("/goods/listAlarm")
    public Map<String,Object> listAlarm(){

        return goodsService.getListAlarm();
    }

}
