package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 分页查询供应商
     * @param page
     * @param rows
     * @param supplierName
     * @return
     */
    @PostMapping("/supplier/list")
    public Map<String,Object> supplierList(Integer page, Integer rows, String supplierName){
        return supplierService.getAllSupplierList(page,rows,supplierName);
    }

//    http://localhost:8080/supplier/save?supplierId=1
   @PostMapping("/supplier/save")
    public ServiceVO updateSupplier(Supplier supplier){
        Integer supplierId = supplier.getSupplierId();
           if (supplierId != null){
               supplierService.updateSupplierById(supplier);
           }else {
               supplierService.addSupplier(supplier);
           }
       return new  ServiceVO(100,"请求成功");
    }

    @PostMapping("/supplier/delete")
    public ServiceVO deleteSupplier(String  ids){
        String[] strings = ids.split(",");
        for (String string : strings) {
            supplierService.delSupplierList(string);
        }
        return new  ServiceVO(100,"请求成功");
    }
}
