package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDao {

    List<Supplier> getAllSupplier(@Param("page")Integer page, @Param("rows")Integer rows, @Param("supplierName")String supplierName);

    void updateSupplierById(Supplier supplier);

    void addSupplierById(Supplier supplier);

    void delSupplierList(String string);

}
