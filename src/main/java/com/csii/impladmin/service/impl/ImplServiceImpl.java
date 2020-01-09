package com.csii.impladmin.service.impl;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Impl;
import com.csii.impladmin.entiy.Kind;
import com.csii.impladmin.repository.ImplRepository;
import com.csii.impladmin.repository.KindRepository;
import com.csii.impladmin.service.ImplService;
import com.csii.impladmin.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ImplServiceImpl implements ImplService {
    @Autowired
    private ImplRepository implRepository;
    @Autowired
    private KindRepository kindRepository;
    @Override
    public List getAll() {
        return implRepository.findAll();
    }

    @Override
    public Impl getOne(Map map) {
        return implRepository.selectByKeyId((Long)map.get(Dict.IID));
    }

    @Override
    public List getImplByKid(Map map) {
        return implRepository.findByKId((Long)map.get(Dict.KID));
    }

    @Override
    public void delOne(Map map) {
       implRepository.deleteById((Long)map.get(Dict.IID));
    }

    @Override
    public void insert(Map map) {
        Kind kind = kindRepository.queryById((Long) map.get(Dict.KID));
        if(Util.isNull(kind)){
            throw new MyException(ErrorDict.KIND_NOT_EXIST);
        }
        if (checkINameExist((String) map.get(Dict.INAME),null)) {
            Impl impl = new Impl();
            impl.setSendType((String) map.get(Dict.SENDTYPE));
            impl.setRspData((String) map.get(Dict.RSPDATA));
            impl.setReqData((String) map.get(Dict.REQDATA));
            impl.setUrl((String) map.get(Dict.URL));
            impl.setIName((String) map.get(Dict.INAME));
            impl.setKId((Long) map.get(Dict.KID));
            Date date = new Date();
            impl.setCreateTime(new Timestamp(date.getTime()));

            implRepository.save(impl);
        }
    }



    @Override
    public void update(Map map) {
        Impl impl = implRepository.selectByKeyId((Long) map.get(Dict.IID));
       if(Util.isNull(impl)){
           throw new MyException(ErrorDict.IMPL_NOT_EXIST);
       }
        if(!Util.isNull(map.get(Dict.KID))){
            Kind kind = kindRepository.queryById((Long) map.get(Dict.KID));
            if(Util.isNull(kind)){
                throw new MyException(ErrorDict.KIND_NOT_EXIST);
            }
            impl.setKId((Long) map.get(Dict.KID));
        }

        if(checkINameExist((String) map.get(Dict.INAME),impl.getIName())) {
            impl.setSendType((String) map.get(Dict.SENDTYPE));
            impl.setRspData((String) map.get(Dict.RSPDATA));
            impl.setReqData((String) map.get(Dict.REQDATA));
            Date date = new Date();
            impl.setUpdateTime(new Timestamp(date.getTime()));
            impl.setUrl((String) map.get(Dict.URL));
            impl.setIName((String) map.get(Dict.INAME));
            implRepository.save(impl);
        }
    }

    private boolean checkINameExist(String iName,String oldIName) {
        if(!Util.isNull(oldIName)&&oldIName.equals(iName)){
            return true;
        }
        Impl impl = implRepository.findByIName(iName);
        if(!Util.isNull(impl)){
            throw new MyException(ErrorDict.INAME_EXIST);
        }
        return true;

    }
}
