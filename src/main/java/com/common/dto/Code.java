package com.common.dto;

public enum Code {
	SCUESS("成功", 200), FAIL("失败", 500);

	private String name;
	private int index;

	private Code(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
