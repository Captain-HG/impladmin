package com.csii.impladmin.service.impl;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Kind;
import com.csii.impladmin.entiy.Product;
import com.csii.impladmin.repository.PKRepository;
import com.csii.impladmin.repository.ProductRepository;
import com.csii.impladmin.service.ProductService;
import com.csii.impladmin.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PKRepository pkRepository;

    @Override
    public List getALL() {
        return productRepository.findAll();
    }

    @Override
    public Product getOne(Map map) {
        return productRepository.queryById((Long) map.get(Dict.PID));
    }

    @Override
    public void insert(Map map) {
        if (checkPNameExist((String) map.get(Dict.PNAME), null)) {
            Product product = new Product();
            product.setPName((String) map.get(Dict.PNAME));
            Date date = new Date();
            product.setCreateTime(new Timestamp(date.getTime()));
            productRepository.save(product);
        }
    }

    @Override
    public void update(Map map) {

        Product product = productRepository.queryById((Long) map.get(Dict.PID));
        if(Util.isNull(product)){
             throw new MyException(ErrorDict.PRODUCT_NOT_EXIST);
         }
        if (checkPNameExist((String) map.get(Dict.PNAME), product.getPName())) {
            product.setPName((String) map.get(Dict.PNAME));
            Date date = new Date();
            product.setUpdateTime(new Timestamp(date.getTime()));
            productRepository.save(product);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delOne(Map map) {
        productRepository.deleteById((Long) map.get(Dict.PID));
        pkRepository.deleteByPId((Long) map.get(Dict.PID));
    }


    private boolean checkPNameExist(String pName, String oldPName) {
        if (!Util.isNull(oldPName) && oldPName.equals(pName)) {
            return true;
        }
        Product product = productRepository.findByPName(pName);
        if (!Util.isNull(product)) {
            throw new MyException(ErrorDict.PNAME_EXIST);
        }
        return true;
    }
}
