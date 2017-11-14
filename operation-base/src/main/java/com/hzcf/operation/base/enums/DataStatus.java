package com.hzcf.operation.base.enums;
/**
 * Create by hanlin on 2017年11月6日
 **/
public enum DataStatus implements EnumType {
    DELETED(-1, "已删除"),
    UNKNOWN(0, "未知"),
    NORMAL(1, "正常"),
    ;
    private final int code;
    private final String text;

    private DataStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String text() {
        return text;
    }

    public static DataStatus codeOf(int code) {
        if (code < 0) {
            return DataStatus.DELETED;
        }
        
        for (DataStatus value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid DataStatus code: " + code);
    }
}
