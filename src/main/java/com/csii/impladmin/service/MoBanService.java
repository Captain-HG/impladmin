package com.csii.impladmin.service;

import com.csii.impladmin.entiy.MoBan;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface MoBanService {
    /**
     * 获取所有的接口数据
     * @return
     */
    List<MoBan> getAll();

    /**
     * 根据主键id查询
     * @param map
     * @return
     */
    MoBan getOne(Map map);

    /**
     * 删除
     * @param map
     */
    void delOne(Map map);

    /**
     * 更新
     * @param map
     */
    void update(Map map);

    /**
     * 插入
     * @param map
     */
    void insert(Map map);
}
