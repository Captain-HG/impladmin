package com.csii.impladmin.service.impl;


import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.MoBan;
import com.csii.impladmin.repository.MoBanRepository;
import com.csii.impladmin.service.MoBanService;
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
public class MoBanServiceimpl implements MoBanService {

    @Autowired
    private MoBanRepository moBanRepository;


    @Override
    public List<MoBan> getAll() {
        return moBanRepository.findAll();
    }

    @Override
    public MoBan getOne(Map map) {
        return moBanRepository.queryById((Long) map.get(Dict.MBID));
    }

    @Override
    public void delOne(Map map) {
        moBanRepository.deleteById((Long) map.get(Dict.MBID));
    }

    @Override
    public void update(Map map) {

        MoBan MoBan = moBanRepository.queryById((Long) map.get(Dict.MBID));
        if(Util.isNull(MoBan)){
           throw new MyException(ErrorDict.MOBAN_NOT_EXITS);
        }
        if (checkMBNameExist((String) map.get(Dict.MBNAME),MoBan.getMbName())) {
            MoBan.setData((String) map.get(Dict.DATA));
            MoBan.setMbType((String) map.get(Dict.MBTYPE));
            MoBan.setMbName((String) map.get(Dict.MBNAME));
            MoBan.setDesc((String) map.get(Dict.DESC));
            Date date = new Date();
            MoBan.setUpdateTime(new Timestamp(date.getTime()));
            moBanRepository.save(MoBan);
        }
    }

    @Override
    public void insert(Map map) {
        if (checkMBNameExist((String) map.get(Dict.MBNAME),null)) {
            MoBan MoBan = new MoBan();
            Date date = new Date();
            MoBan.setCreateTime(new Timestamp(date.getTime()));
            MoBan.setData((String) map.get(Dict.DATA));
            MoBan.setMbType((String) map.get(Dict.MBTYPE));
            MoBan.setMbName((String) map.get(Dict.MBNAME));
            MoBan.setDesc((String) map.get(Dict.DESC));
            moBanRepository.save(MoBan);
        }
    }




    private boolean checkMBNameExist(String MBName,String oldMBName) {
        if(!Util.isNull(oldMBName)&&oldMBName.equals(MBName)){
            return true;
        }
        MoBan mb = moBanRepository.findByMbName(MBName);
        if(!Util.isNull(mb)){
            throw new MyException(ErrorDict.MBNAME_EXIST);
        }
        return true;
    }

}

