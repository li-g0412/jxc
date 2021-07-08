package com.atguigu.jxc.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface GoodsTypeService {
    Map<String, Object> getAllGoodsList();

    String selectGoodsType();

    void insGoodsType(String goodsTypeName, Integer pId);

    void delGoodsType(Integer goodsTypeId);
}
