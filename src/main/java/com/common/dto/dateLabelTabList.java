package com.common.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class dateLabelTabList<T> implements Comparable<dateLabelTabList>{
	
	private String type;
	private String date;
	private Timestamp time;
	private Integer size;   //分类集合的数量
	private ArrayList<T> list;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getSize() {
		if(this.list==null) return 0;
		else return this.list.size();
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public ArrayList<T> getList() {
		return list;
	}
	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	
	@Override
	public int compareTo(dateLabelTabList o) {
		return this.time.compareTo(o.getTime());
	}

	
}
