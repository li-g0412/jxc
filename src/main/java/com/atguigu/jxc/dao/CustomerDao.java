package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {

    List<Supplier> getAllCustomerList(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName);

    void updateCustomerById(Customer customer);

    void addCustomerById(Customer customer);

    void delCustomerList(String string);

}
