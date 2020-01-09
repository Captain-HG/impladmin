package com.csii.impladmin.config;

/**
 * @AUTHOR HG-captain
 * @Data 2019/12/30
 * @Description
 */
public interface ErrorCode {

    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getDescription();
}
