package com.csii.impladmin.controller;

import com.alibaba.fastjson.JSON;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.MoBan;

import com.csii.impladmin.service.MoBanService;
import com.csii.impladmin.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
@RestController
@RequestMapping("/MoBan")
public class MoBanController {
    @Autowired
    private MoBanService moBanService;

    /**
     * 获取所有接口
     *
     * @return
     */
    @RequestMapping("/getAll")
    public List<MoBan> getAll() {
        return moBanService.getAll();
    }

    /**
     * 主键id查询
     *
     * @param map
     * @return
     */
    @RequestMapping("/getOne")
    public MoBan getOne(@RequestBody Map map) {
        checkMbId(map);
        return moBanService.getOne(map);
    }

    /**
     * 更新某个接口
     *
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody Map map) {
        checkMoBanIsNull(map);
        checkMbId(map);
        if(!Util.isNull(map.get(Dict.DATA))) {
            JSON data = (JSON) JSON.toJSON(map.get(Dict.DATA));
            String dataContent = data.toJSONString();
            map.put(Dict.DATA, dataContent);
        }
        moBanService.update(map);
        return "success";
    }

    /**
     * 删除某个接口数据
     *
     * @param map
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(@RequestBody Map map) {

        checkMbId(map);
        moBanService.delOne(map);
        return "success";
    }

    @RequestMapping("/insert")
    public String insert(@RequestBody Map map) {

        checkMoBanIsNull(map);
        if(!Util.isNull(map.get(Dict.DATA))) {
            JSON data = (JSON) JSON.toJSON(map.get(Dict.DATA));
            String dataContent = data.toJSONString();
            map.put(Dict.DATA, dataContent);
        }
        moBanService.insert(map);
        return "success";

    }

    private boolean checkMoBanIsNull(Map map) {
        if(Util.isNull(map)){
            throw new MyException("缺少数据参数");
        }
//        if (Util.isNull(map.get(Dict.DATA))) {
//            throw new MyException("缺少数据参数");
//        }
        if (Util.isNull(map.get(Dict.DESC))) {
            throw new MyException(ErrorDict.DESC_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.MBTYPE))) {
            throw new MyException(ErrorDict.MBTYPE_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.MBNAME))) {
            throw new MyException(ErrorDict.MBNAME_REQUIRED);
        }
        return true;

    }

    /**
     * 校验模板id
     * @param map
     */
    private void checkMbId(Map map){
        if (Util.isNull(map)||Util.isNull(map.get(Dict.MBID))) {
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        map.put(Dict.MBID,Util.checkLongId(map.get(Dict.MBID)));
    }


}
