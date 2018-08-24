package com.common.tool;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> implements Serializable {

    private int code;
    private T data;
    private T list;
    private String msg;
    private Integer count;
    public static final int SUCCESS=200;
    public static final int SERVICE_ERROR=500;

    public DataResponse(int code, T list, String msg) {
        this.code = code;
        this.list = list;
        this.msg = msg;
    }

    public DataResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }
    
    public DataResponse(int code, T list,Integer count) {
        this.code = code;
        this.list = list;
        this.count = count;
    }

    public DataResponse() {
        this.code=SUCCESS;
    }
    public DataResponse(T data){
        this();
        this.data=data;
    }
    public DataResponse(String msg){
        this();
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public T getList() {
		return list;
	}

	public void setList(T list) {
		this.list = list;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
    
    
}
