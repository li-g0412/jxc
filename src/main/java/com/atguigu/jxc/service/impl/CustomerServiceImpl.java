package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.service.SupplierService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;


    @Override
    public Map<String, Object> getAllCustomerList(Integer page, Integer rows, String customerName) {
        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Supplier> customerList = customerDao.getAllCustomerList(page,rows,customerName);
        map.put("rows",customerList);
        map.put("total",customerList.size());
        return map;
    }

    @Override
    public void updateCustomerById(Customer customer) {
        customerDao.updateCustomerById(customer);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomerById(customer);
    }

    @Override
    public void delCustomerList(String string) {
        customerDao.delCustomerList(string);
    }
}
