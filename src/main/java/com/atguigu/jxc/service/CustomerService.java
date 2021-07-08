package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Supplier;

import java.util.Map;

public interface CustomerService {
    Map<String, Object> getAllCustomerList(Integer page, Integer rows, String customerName);

    //修改供应商信息
    void updateCustomerById(Customer customer);

    //添加供应商信息
    void addCustomer(Customer customer);

    //删除供应商信息
    void delCustomerList(String string);
}
