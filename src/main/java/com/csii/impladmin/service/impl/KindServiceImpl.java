package com.csii.impladmin.service.impl;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Impl;
import com.csii.impladmin.entiy.Kind;
import com.csii.impladmin.repository.ImplRepository;
import com.csii.impladmin.repository.KindRepository;
import com.csii.impladmin.repository.PKRepository;
import com.csii.impladmin.service.KindService;
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
public class KindServiceImpl implements KindService {
    @Autowired
    private KindRepository kindRepository;
    @Autowired
    private PKRepository pkRepository;
    @Autowired
    private ImplRepository implRepository;
    @Override
    public List getAll() {
        return kindRepository.findAll();
    }
    @Override
    public Kind getOne(Map map) {

        return kindRepository.queryById((Long)map.get(Dict.KID));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delOne(Map map) {
        pkRepository.deleteByKId((Long) map.get(Dict.KID));
        implRepository.deleteByKId((Long) map.get(Dict.KID));
        kindRepository.deleteById((Long) map.get(Dict.KID));
    }

    @Override
    public void insert(Map map) {
        if(checkKnameExist((String) map.get(Dict.KNAME),null)) {
            Kind kind = new Kind();
            kind.setKName((String) map.get(Dict.KNAME));
            Date date = new Date();
            kind.setCreateTime(new Timestamp(date.getTime()));
            kindRepository.save(kind);
        }
    }



    @Override
    public void update(Map map) {

        Kind kind = kindRepository.queryById((Long) map.get(Dict.KID));
        if(Util.isNull(kind)){
            throw new MyException(ErrorDict.KIND_NOT_EXIST);
        }
        if(checkKnameExist((String) map.get(Dict.KNAME),kind.getKName())) {
            kind.setKName((String) map.get(Dict.KNAME));
            Date date = new Date();
            kind.setUpdateTime(new Timestamp(date.getTime()));
            kindRepository.save(kind);
        }
    }

    private boolean checkKnameExist(String kName,String oldKName) {
        if(!Util.isNull(oldKName)&&oldKName.equals(kName)){
            return true;
        }
        Kind kind = kindRepository.findByKName(kName);
        if(!Util.isNull(kind)){
            throw new MyException(ErrorDict.KNAME_EXIST);
        }
        return true;
    }
}
