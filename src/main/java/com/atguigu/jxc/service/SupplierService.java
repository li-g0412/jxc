package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;

import java.util.Map;

public interface SupplierService {
    Map<String, Object> getAllSupplierList(Integer page, Integer rows, String supplierName);

    //修改供应商信息
    void updateSupplierById(Supplier supplier);

    //添加供应商信息
    void addSupplier(Supplier supplier);

    //删除供应商信息
    void delSupplierList(String string);
}
