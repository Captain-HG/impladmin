package com.csii.impladmin.config;


/**
 * @AUTHOR HG-captain
 * @Data 2019/12/30
 * @Description
 */

public class MyException extends RuntimeException {

    private static final long serialVersionUID = -7864604160297181941L;

    /** 错误码 */
    protected final ErrorCode errorCode;

    /**
     * 这个是和谐一些不必要的地方,冗余的字段
     * 尽量不要用
     */
    private String code;






    /**
     * 构造通用异常
     * @param errorCode 错误码
     * @param detailedMessage 详细描述
     */
    public MyException(final ErrorCode errorCode, final String detailedMessage) {
        super(detailedMessage);
        this.errorCode = errorCode;
    }
    /**
     * 系统异常提示
     * @param
     */
    public MyException(final String msg) {
        super(msg);
        this.errorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "500";
            }

            @Override
            public String getDescription() {
                return msg;
            }
        };
    }
    /**
     * 构造通用异常
     * @param errorCode 错误码
     */
    public MyException(final ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }


}
