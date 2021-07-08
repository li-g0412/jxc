package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public Map<String, Object> getAllSupplierList(Integer page, Integer rows, String supplierName) {
        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Supplier> supplierList = supplierDao.getAllSupplier(page,rows,supplierName);
        map.put("rows",supplierList);
        map.put("total",supplierList.size());
        return map;
    }

    @Override
    public void updateSupplierById(Supplier supplier) {
        supplierDao.updateSupplierById(supplier);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierDao.addSupplierById(supplier);
    }

    @Override
    public void delSupplierList(String string) {
        supplierDao.delSupplierList(string);
    }
}
