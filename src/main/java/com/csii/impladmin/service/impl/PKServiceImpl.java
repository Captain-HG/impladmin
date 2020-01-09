package com.csii.impladmin.service.impl;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.ProKind;
import com.csii.impladmin.repository.KindRepository;
import com.csii.impladmin.repository.PKRepository;
import com.csii.impladmin.repository.ProductRepository;
import com.csii.impladmin.service.PKService;
import com.csii.impladmin.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
@Service
public class PKServiceImpl implements PKService {
    @Autowired
    private PKRepository pkRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KindRepository kindRepository;
    @Override
    public List queryByPId(Map map) {

        return pkRepository.queryByPId((Long) map.get(Dict.PID));
    }

    @Override
    public void insert(Map map) {
        Long kId = (Long) map.get(Dict.KID);
        Long pId =(Long) map.get(Dict.PID);
        if(Util.isNull(productRepository.queryById(pId))){
           throw new MyException(ErrorDict.PRODUCT_NOT_EXIST);
        }
        if(Util.isNull(kindRepository.queryById(kId))){
            throw new MyException(ErrorDict.KIND_NOT_EXIST);
        }
        ProKind prokind = pkRepository.findByPIdAndKId(pId, kId);
        if(!Util.isNull(prokind)){
            throw new MyException(ErrorDict.PK_EXIST);
        }
        ProKind pk = new ProKind();
        pk.setKId(kId);
        pk.setPId(pId);
        pkRepository.save(pk);
    }

    @Override
    public List getAll() {
        return pkRepository.findAll();
    }


}
