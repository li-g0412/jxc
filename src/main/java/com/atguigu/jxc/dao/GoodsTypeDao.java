package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Menu;
import com.atguigu.jxc.entity.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {



    Integer updateGoodsTypeState(GoodsType parentGoodsType);

    List<Unit> getAllGoods();

    List<GoodsType> getAllGoodsType(int parentId);

    void insertGoodsType(GoodsType goodsType);

    GoodsType getGoodsTypeById(Integer pId);

    void updateGoodsTypeStatus(GoodsType parentGoodsType);

    List<GoodsType> getGoodsTypeByPid(Integer pId);

    void deleteGoodsTypeById(Integer goodsTypeId);

    void updateParentToChildren(Integer pId);
}
