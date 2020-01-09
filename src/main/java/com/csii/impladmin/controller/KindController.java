package com.csii.impladmin.controller;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Kind;
import com.csii.impladmin.service.KindService;
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
@RequestMapping("/kind")
public class KindController {
    @Autowired
    private KindService kindService;

    /**
     * 查询所有类别的数据
     * @return
     */
    @RequestMapping("/getAll")
    public List getkindAll(){
        return kindService.getAll();
    }

    /**
     * 主键查询单个kind
     * @param map
     * @return
     */
    @RequestMapping("/getOne")
    public Kind getOne(@RequestBody Map map){
       checkId(map);
       return kindService.getOne(map);
    }

    /**
     * 根据主键删除
     * @param map
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(@RequestBody Map map){
        checkId(map);
        kindService.delOne(map);
        return "success";
    }

    /**
     * 插入
     * @param map
     * @return
     */
    @RequestMapping("/insert")
    public String insert(@RequestBody Map map){
        if(Util.isNull(map)||Util.isNull(map.get(Dict.KNAME))){
            throw new MyException(ErrorDict.KNAME_REQUIRED);
        }
        kindService.insert(map);
        return "success";
    }

    /**
     * 根据主键更新
     * @param map
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody Map map){
        checkId(map);
        if(Util.isNull(map)||Util.isNull(map.get(Dict.KNAME))){
            throw new MyException(ErrorDict.KNAME_REQUIRED);
        }
        kindService.update(map);
        return  "success";
    }

    /**
     * 校验id
     * @param map
     * @return
     */
    private final void checkId(Map map){
        if(Util.isNull(map)||Util.isNull(map.get(Dict.KID))){
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        map.put(Dict.KID,Util.checkLongId(map.get(Dict.KID)));

    }


}
