package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 分页查询供客户
     * @param page
     * @param rows
     * @param customerName
     * @return
     */
    @PostMapping("/customer/list")
    public Map<String,Object> customerList(Integer page, Integer rows, String customerName){
        return customerService.getAllCustomerList(page,rows,customerName);
    }

//    http://localhost:8080/supplier/save?supplierId=1
   @PostMapping("/customer/save")
    public ServiceVO updateSupplier(Customer customer){
        Integer customerId = customer.getCustomerId();
           if (customerId != null){
               customerService.updateCustomerById(customer);
           }else {
               customerService.addCustomer(customer);
           }
       return new  ServiceVO(100,"请求成功");
    }

    @PostMapping("/customer/delete")
    public ServiceVO deleteSupplier(String  ids){
        String[] strings = ids.split(",");
        for (String string : strings) {
            customerService.delCustomerList(string);
        }
        return new  ServiceVO(100,"请求成功");
    }
}
