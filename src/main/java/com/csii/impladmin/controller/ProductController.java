package com.csii.impladmin.controller;

import com.csii.impladmin.common.Dict;
import com.csii.impladmin.common.ErrorDict;
import com.csii.impladmin.config.MyException;
import com.csii.impladmin.entiy.Product;
import com.csii.impladmin.service.PKService;
import com.csii.impladmin.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PKService pkService;

    /**
     * 查询所有的项目
     *
     * @return
     */
    @RequestMapping("/getAll")
    public List getProductAll() {
        return productService.getALL();
    }

    /**
     * 根据id查询
     *
     * @param map
     * @return
     */
    @RequestMapping("/getOne")
    public Product getOne(@RequestBody Map map) {
        checkPId(map);
        return productService.getOne(map);
    }

    /**
     * 通过pid查询所包含的kind
     *
     * @param map
     * @return
     */
    @RequestMapping("/getPK")
    public List getPK(@RequestBody Map map) {
        checkPId(map);
        return pkService.queryByPId(map);
    }

    /**
     * 插入
     *
     * @param map
     * @return
     */
    @RequestMapping("/insert")
    public String insert(@RequestBody Map map) {
        checkProduct(map);
        productService.insert(map);
        return "success";
    }


    /**
     * 插入pk
     *
     * @param map
     * @return
     */
    @RequestMapping("/pkInsert")
    public String pkInsert(@RequestBody Map map) {
        if (Util.isNull(map) || Util.isNull(map.get(Dict.KID)) || Util.isNull(map.get(Dict.KID))) {
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        map.put(Dict.PID, Util.checkLongId(map.get(Dict.PID)));
        map.put(Dict.KID, Util.checkLongId(map.get(Dict.KID)));
        pkService.insert(map);
        return "success";
    }
    /**
     * 插入pk
     *
     * @param map
     * @return
     */
    @RequestMapping("/getAllPk")
    public List getAllPk(@RequestBody Map map) {

        return pkService.getAll();
    }

    /**
     * 更新
     *
     * @param map
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody Map map) {
        checkPId(map);
        checkProduct(map);
        productService.update(map);
        return "success";
    }

    /**
     * 删除单个项目
     *
     * @param map
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(@RequestBody Map map) {
        checkPId(map);
        productService.delOne(map);
        return "success";
    }

    /**
     * 校验入参
     *
     * @param map
     */
    private void checkProduct(Map map) {
        if (Util.isNull(map)) {
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        if (Util.isNull(map.get(Dict.PNAME))) {
            throw new MyException(ErrorDict.PNAME_REQUIRED);
        }
    }


    /**
     * 校验id
     *
     * @param map
     * @return
     */
    private final void checkPId(Map map) {
        if (Util.isNull(map) || Util.isNull(map.get(Dict.PID))) {
            throw new MyException(ErrorDict.PARAM_REQUIRED);
        }
        map.put(Dict.PID, Util.checkLongId(map.get(Dict.PID)));

    }


}
