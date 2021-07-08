package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();

    List<Goods> getAllGoods(@Param("page")Integer page, @Param("rows")Integer rows, @Param("codeOrName")String codeOrName, @Param("goodsTypeId")Integer goodsTypeId);

    List<Goods> getAllGoodsType(@Param("page")Integer page, @Param("rows")Integer rows, @Param("nameOrCode") String nameOrCode);

    List<Goods> getHasInventoryMap(@Param("page")Integer page, @Param("rows")Integer rows, @Param("nameOrCode") String nameOrCode);

    void saveStock(@Param("goodsId")Integer goodsId, @Param("inventoryQuantity")Integer inventoryQuantity,@Param("purchasingPrice")double purchasingPrice);

    int delStockById(Integer goodsId);

    List<Supplier> getAllSupplier(@Param("page")Integer page, @Param("rows")Integer rows,@Param("goodsName") String goodsName,@Param("goodsTypeId") Integer goodsTypeId);

    void add(Goods goods);

    void update(Goods goods);

    void delete(Integer goodsId);

    List<Goods> selectAlarmList();
}
