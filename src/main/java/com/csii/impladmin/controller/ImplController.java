package com.csii.impladmin.controller;

import com.alibaba.fastjson.JSON;
import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Impl;
import com.csii.impladmin.service.ImplService;
import com.csii.impladmin.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/8
 * @Description
 */
@RestController
@RequestMapping("/impl")
public class ImplController {

    @Autowired
    private ImplService implService;

    /**
     * 查询所有的接口
     * @return
     */
    @RequestMapping("/getAll")
    public List getImplAll(){
        return implService.getAll();
    }

    /**
     * 主键查询单个
     * @param map
     * @return
     */
    @RequestMapping("/getOne")
    public Impl getOne(@RequestBody Map map){
        checkId(map);
      return  implService.getOne(map);
    }

    /**
     * 根据类别id查询接口
     * @param map
     * @return
     */
    @RequestMapping("/getImpls")
    public List getImplByKid(@RequestBody Map map){
        if(Util.isNull(map)||Util.isNull(map.get(Dict.KID))){
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        if( map.get(Dict.KID) instanceof String) {
            long id = Long.parseLong((String) map.get(Dict.KID));
            map.put(Dict.KID,id);
        }

       return implService.getImplByKid(map);
    }

    /**
     * 主键删除单个接口
     * @param map
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(@RequestBody Map map){
        checkId(map);
        implService.delOne(map);
        return "success";
    }

    @RequestMapping("/insert")
    public String insert(@RequestBody Map map){
        checkImpl(map);
        jsonData(map);
        implService.insert(map);
        return "success";
    }



    @RequestMapping("/update")
    public String update(@RequestBody Map map){
        checkId(map);
//        checkImpl(map);
        jsonData(map);
        implService.update(map);
        return "success";
    }

    /**
     * json转换数据
     * @param map
     */
    private void jsonData(Map map) {
        if(!Util.isNull(map.get(Dict.REQDATA))) {
            JSON req = (JSON) JSON.toJSON(map.get(Dict.REQDATA));
            map.put(Dict.REQDATA,req.toJSONString());
        }
        if(!Util.isNull(map.get(Dict.RSPDATA))) {
            JSON rsp = (JSON) JSON.toJSON(map.get(Dict.RSPDATA));
            map.put(Dict.RSPDATA, rsp.toJSONString());
        }
    }

    /**
     * 校验id
     * @param map
     * @return
     */
    private final void checkId(Map map){
        if(Util.isNull(map)||Util.isNull(map.get(Dict.IID))){
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        map.put(Dict.IID,Util.checkLongId(map.get(Dict.IID)));
    }

    /**
     * 校验入参
     * @param map
     */
    private boolean checkImpl(Map map) {
        if (Util.isNull(map.get(Dict.INAME))) {
            throw new MyException(ErrorDict.INAME_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.SENDTYPE))) {
            throw new MyException(ErrorDict.SENDTYPE_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.URL))) {
            throw new MyException(ErrorDict.URL_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.KID))) {
            throw new MyException(ErrorDict.KID_REQUIRED);
        }
        map.put(Dict.KID,Util.checkLongId(map.get(Dict.KID)));
//        if (Util.isNull(map.get(Dict.REQDATA))) {
//            throw new MyException(ErrorDict.REQDATA_REQUIRED);
//        }
//        if (Util.isNull(map.get(Dict.RSPDATA))) {
//            throw new MyException(ErrorDict.RSPDATA_REQUIRED);
//        }
        return true;
    }
}
