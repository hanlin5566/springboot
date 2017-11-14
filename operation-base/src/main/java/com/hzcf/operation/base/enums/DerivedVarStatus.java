package com.hzcf.operation.base.enums;

/**
 * Create by hanlin on 2017年11月8日
 **/
public enum DerivedVarStatus implements EnumType {
	// 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
	UNKNOWN(0, "未知"), 
	SAVED(1, "已保存"), 
	COMPILED(2, "通过编译"), 
	PUBLISHED(3, "已发布"),
	;
	private final int code;
	private final String text;

	private DerivedVarStatus(int code, String text) {
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

	public static DerivedVarStatus codeOf(int code) {
		for (DerivedVarStatus value : values()) {
			if (value.code == code) {
				return value;
			}
		}

		throw new IllegalArgumentException("Invalid DerivedVarStatus code: " + code);
	}
}
