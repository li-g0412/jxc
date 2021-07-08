package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Menu;
import com.atguigu.jxc.entity.Role;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.GoodsTypeService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public Map<String, Object> getAllGoodsList() {
        Map<String,Object> map = new HashMap<>();
        List<Unit> allGoods = goodsTypeDao.getAllGoods();
        map.put("rows",allGoods);
        map.put("total",allGoods.size());
        return map;
    }

    @Override
    public String selectGoodsType() {
            return this.getGoodsType(-1).toString();// 根节点默认从-1开始
        }

        //添加分类
    @Override
    public void insGoodsType(String goodsTypeName, Integer pId) {
        GoodsType goodsType = new GoodsType();
        goodsType.setGoodsTypeName(goodsTypeName);
        goodsType.setPId(pId);
        goodsType.setGoodsTypeState(0);
        GoodsType parentGoodsType = goodsTypeDao.getGoodsTypeById(pId);
        if(parentGoodsType.getGoodsTypeState()==0){
            parentGoodsType.setGoodsTypeState(1);
            goodsTypeDao.updateGoodsTypeStatus(parentGoodsType);
        }
        goodsTypeDao.insertGoodsType(goodsType);
    }

    @Override
    public void delGoodsType(Integer goodsTypeId) {
        //查询当前节点的父节点的id下的节点是否大于1
        GoodsType goodsTypeById = goodsTypeDao.getGoodsTypeById(goodsTypeId);
        List<GoodsType> goodsTypes = goodsTypeDao.getGoodsTypeByPid(goodsTypeById.getPId());
        if(goodsTypes!=null && goodsTypes.size()>1){
            goodsTypeDao.deleteGoodsTypeById(goodsTypeId);
        }else if (goodsTypes!=null && goodsTypes.size()<=1){
            goodsTypeDao.deleteGoodsTypeById(goodsTypeId);
            goodsTypeDao.updateParentToChildren(goodsTypeById.getPId());
        }
    }

    public JsonArray getGoodsType(int parentId){
        JsonArray array = this.getGoodsTypeById(parentId);
        for (int i = 0;i < array.size();i++){
            JsonObject object = (JsonObject) array.get(i);
            if (object.get("state").getAsString().equals("open")){
                continue;
            }else {
                object.add("children",this.getGoodsType(object.get("id").getAsInt()));
            }
        }
        return array;
    }

    private JsonArray getGoodsTypeById(int parentId) {
        JsonArray array = new JsonArray();
        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsType(parentId);
        for (GoodsType goodsType : goodsTypeList) {
            JsonObject object = new JsonObject();
            object.addProperty("id",goodsType.getGoodsTypeId());
            object.addProperty("text",goodsType.getGoodsTypeName());
            object.addProperty("iconCls","goods-type");
            JsonObject attributes = new JsonObject();

            if (goodsType.getGoodsTypeState()==1){
                object.addProperty("state","closed");
                attributes.addProperty("state",1);
            }else {
                object.addProperty("state","open");
                attributes.addProperty("state",0);
            }
            object.add("attributes",attributes);
            array.add(object);
        }

        return array;
    }
}
