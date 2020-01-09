package com.csii.impladmin.config;

/**
 * @author 水银队长
 */
public enum ErrCodeEn implements ErrorCode {
    /**
     * 404错误码
     */
    E_404("404","页面找不到"),
    E_500("500","系统繁忙"),
    ;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return msg;
    }

    private String code;
    private  String msg;

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrCodeEn(String code, String msg ){
     this.code=code;
     this.msg=msg;
    }



}
