package com.csii.impladmin.service;

import com.csii.impladmin.entiy.Kind;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface KindService {
    /**
     * 获取所有的类别数据
     * @return
     */
    List getAll();

    /**
     * 根据kid查询kind
     * @param map
     * @return
     */
    Kind getOne(Map map);

    /**
     * 主键删除
     * @param map
     */
    void delOne(Map map);

    /**
     * 插入
     * @param map
     */
    void insert(Map map);

    /**
     * 跟新
     * @param map
     */
    void update(Map map);
}
